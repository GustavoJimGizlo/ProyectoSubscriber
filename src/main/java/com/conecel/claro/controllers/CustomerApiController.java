package com.conecel.claro.controllers;

import java.util.UUID;

import org.apache.logging.log4j.CloseableThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.conecel.claro.controllers.dto.Constants;
import com.conecel.claro.controllers.dto.QuerySubscriberResponseMessage;
import com.conecel.claro.exceptions.IntegrationException;
import com.conecel.claro.service.SubscriberService;

import io.swagger.annotations.ApiParam;
import lombok.extern.log4j.Log4j2;
@Log4j2
@Controller
public class CustomerApiController implements CustomerApi {
	
	
	@Autowired
	private SubscriberService subscriberService;
	
	public ResponseEntity<QuerySubscriberResponseMessage> querySubscriber(
			@ApiParam(value = "IdentificaciÃ³n unÃ­voca del subscriptor que se desea consultar a travÃ©s de Ã©sta operaciÃ³n.", required = true)
			@PathVariable("subscriberId") String subscriberId,
			@ApiParam(value = "IdentificaciÃ³n de la transacciÃ³n.",required = false)
			@RequestHeader(name = "externalTransactionId", required = false) String externalTransactionId) {
		return this.querySubscriber(null, subscriberId, externalTransactionId);
	}
	
	
	public ResponseEntity<QuerySubscriberResponseMessage> querySubscriber(
			@ApiParam(value = "Indica el tipo de identificaciÃ³n del subscriptor que se estÃ¡ suministrando para consultar la informaciÃ³n del mismo. EJEMPLO: SERVICENUMBER, IMSI, IMEI, ICCID") 
			@PathVariable("subscriberType") String subscriberType,
			@ApiParam(value = "IdentificaciÃ³n unÃ­voca del subscriptor que se desea consultar a travÃ©s de Ã©sta operaciÃ³n.", 
				required = true)
			@PathVariable("subscriberId") String subscriberId,
			@ApiParam(value = "IdentificaciÃ³n de la transacciÃ³n.", required = false)
			@RequestHeader(name = "externalTransactionId", required = false) String externalTransactionId) {
		
		log.trace("Ejecutando Metodo QuerySubscriber... ");
		if(externalTransactionId == null)
			externalTransactionId = UUID.randomUUID().toString();
		CloseableThreadContext.Instance ctc = CloseableThreadContext.put("id", externalTransactionId);
		try{		
			log.trace("Retornando informacion por QuerySubscriberResponseMessage...");
			log.info("Mostrando entrada... " + "SubscriberType: "+ subscriberType+ " - "  +"SubscriberId: "+ subscriberId);
			log.debug("Mostrando salida...");
			log.info("Respuesta: " + "Message: " + Constants.MESSAGE+" - "
					+ "SubscriberId: "+ subscriberId + " - "
					+ "SubscriptionType: "+ this.subscriberService.querySubscriberOrchestation(subscriberType, subscriberId).getSubscriptionType().toString()+ " - "
					+ "Name: "+ this.subscriberService.querySubscriberOrchestation(subscriberType, subscriberId).getName()+ " - "
					+ "LastName: "+ this.subscriberService.querySubscriberOrchestation(subscriberType, subscriberId).getLastName()+ " - "
					+ "Status: "+ this.subscriberService.querySubscriberOrchestation(subscriberType, subscriberId).getStatus()+ " - ");
						
			return new ResponseEntity<QuerySubscriberResponseMessage>(
					new QuerySubscriberResponseMessage(
							this.subscriberService.querySubscriberOrchestation(subscriberType, subscriberId), externalTransactionId), 
					HttpStatus.OK);
			
		}catch (IntegrationException e) {
				log.trace("Ocurrio un error en IntegrationException... ");
				return new ResponseEntity<QuerySubscriberResponseMessage>(
						new QuerySubscriberResponseMessage(e.getCode(), e.getMessage(), externalTransactionId), 
						HttpStatus.OK);
				
			}catch (Exception e) {
				log.trace("Ocurrio un error general " + e);
				return new ResponseEntity<QuerySubscriberResponseMessage>(
						new QuerySubscriberResponseMessage(99, String.format("Ocurrio un error general: %s", e.getMessage()), externalTransactionId), 
						HttpStatus.INTERNAL_SERVER_ERROR);
 
		}finally {
			ctc.close();
		}
			
		}
		

}
	