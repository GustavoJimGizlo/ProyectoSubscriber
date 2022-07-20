package com.conecel.claro.integration;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.claro.micro.eis.grpc.Inputs;
import com.claro.micro.eis.grpc.InvokeRequest;
import com.claro.micro.eis.grpc.InvokeResponse;
import com.claro.micro.eis.grpc.InvokeXMLServiceGrpc;
import com.conecel.claro.controllers.dto.Constants;
import com.conecel.claro.controllers.dto.Identification;
import com.conecel.claro.controllers.dto.Identification.TypeEnum;
import com.conecel.claro.controllers.dto.Subscriber;
import com.conecel.claro.controllers.dto.Subscriber.CustomerPartyRoleEnum;
import com.conecel.claro.controllers.dto.Subscriber.SubscriberTypeEnum;
import com.conecel.claro.controllers.dto.Subscriber.SubscriptionTypeEnum;
import com.conecel.claro.exceptions.IntegrationException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import lombok.extern.log4j.Log4j2;
@Log4j2
@Service
public class IntegrationService {

		
	@Qualifier("restTemplate")
	@Autowired
	RestTemplate restTemplate;

	@Value("${url.subscriberInquiryOne}")
	private String urlSubscriberInquiryOne;

	@Value("${url.subscriberInquiryHW}")
	private String urlSubscriberInquiryHW;

	@Value("${grpc.ip.microeis}")
	private String ipMicroeis;

	@Value("${grpc.port.microeis}")
	private int portMicroeis;

	@Autowired
	private ObjectMapper objectMapper;

	/**
	 * Trae un Boleano si el Subscriber pertenece a Hw One
	 * 
	 * @return vadidateResult
	 */

	public boolean validateSubscriber(SubscriberTypeEnum subscriberType, String subscriberId) throws IntegrationException {
		try {
			log.trace("Ejecutando Metodo para validar en Huawei...");
			String subsType = subscriberType.toString();
			if (subscriberType.equals(SubscriberTypeEnum.SERVICENUMBER))
				subsType = "ServiceNumber";
			String pattern = "yyyy-MM-dd'T'HH:mm:ss";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			log.trace("Validando subscriberType = " + subsType); 

			log.trace("Llenando header en el Post...");
			JSONObject header = new JSONObject();
			header.put("channelId", "Subscriber");
			header.put("companyId", "Claro");
			header.put("consumerId", "");
			header.put("consumerProfileId", "");
			header.put("externalOperation", "querySubscriber");
			header.put("externalTransactionDate", simpleDateFormat.format(Calendar.getInstance().getTime()));
			log.trace("Mostrando contenido del header = " + header.toString());

			log.trace("Llenando la peticion para el post..");
			JSONObject root = new JSONObject();
			root.put("header", header);
			root.put("subscriberIdentifierId", subscriberId);
			root.put("subscriberIdentifierType", subsType);
			root.put("validate", "Migration");
			log.trace("Mostrando contenido de la peticion = "+ root.toString());

			log.trace("Invocando el Post para la respuesta...");
			String respuesta = invokePost(urlSubscriberInquiryOne.toString(), root.toString());
			JsonNode rootNode = objectMapper.readTree(respuesta);
			log.trace("Generando respuesta... "+ respuesta);
			log.trace("VALIDANDO INFORMACION TRUE OR FALSE... ");
			JsonNode validate = rootNode.path("validateSubscriber");
			return validate.asBoolean();

		} catch(IntegrationException e) {
			throw IntegrationException.builder()
				.code(e.getCode())
				.message("Hubo un problema al consumir el servicio para validacion de suscriptores en Huawei")
				.technicalMessage(e.getMessage()).build();
			
		}catch (IOException e) {
			throw IntegrationException.builder()
				.code(90)
				.message("Hubo un problema al serializar respuesta obtenida del servicio para validacion de suscriptores en Huawei")
				.technicalMessage(e.getMessage()).build();
}
	}

	/**
	 * MUESTRA INFORMACION VALIDADA EN ONE HW POR VALIDATE SUBSCRIBER
	 * 
	 * @return response
	 */
	public Subscriber suscriberInquiryHW(SubscriberTypeEnum subscriberType, String subscriberId)
			throws IntegrationException {
		try {
			log.trace("Ejecutando Metodo Get para Obtener informacion en InquiryHW...");
			String subsType = subscriberType.toString();
			if (subscriberType.equals(SubscriberTypeEnum.SERVICENUMBER))
				subsType = "serviceNumber";
			log.trace("Validando subscriberType = " + subsType); 
			
			log.trace("Mapeando Parametros y cabecera de la peticion...");
			Map<String, String> params = new HashMap<>();
			params.put(subsType, subscriberId);
			log.trace("Parametros = "+params.toString());

			Map<String, String> cabeceras = new HashMap<>();
			cabeceras.put("X-OperatorId", "101");
			cabeceras.put("X-RegionId", "101");
			cabeceras.put("X-OriginChannel", "69");
			cabeceras.put("X-BEId", "101");
			log.trace("Cabecera = " + cabeceras.toString());
			
			log.trace("Preparando respuesta del Get en la peticion...");
			String respuesta = invokeGet(urlSubscriberInquiryHW.toString(), params, cabeceras);
			JsonNode rootNode = objectMapper.readTree(respuesta);
			log.trace("Leyendo respuesta de la peticion..." + rootNode.toString());
			
			log.trace("Escribiendo informacion obtenido de ser True del Post, a la peticion Get... ");
			Subscriber subscriber = new Subscriber();
			subscriber.setName(rootNode.path("userCustomer").path("name").path("firstName").asText());
			subscriber.setLastName(rootNode.path("userCustomer").path("name").path("lastName").asText());
			subscriber.setStatus(rootNode.path("subscriber").path("subscriberInformation").path("status").asText());
			subscriber.setSubscriptionId(subscriberId);
			Identification id = new Identification();
			id.setValue(rootNode.path("userCustomer").path("id").asText());
			id.setType(TypeEnum.CUSTOMERID);
			subscriber.setIdentification(id);
			log.trace("Leyendo informacion ingresada para la peticion... "+ subscriber.toString());

			subscriber.setSubscriptionType(SubscriptionTypeEnum
					.fromValue(rootNode.path("subscriber").path("subscriberInformation").path("paymentType").asText()));

			log.trace("Verificando si el subscriber es de Organization or Individual...");
			if (rootNode.path("subscriberOwnership").path("ownerCustomer").path("individual") != null) {
				subscriber.setCustomerPartyRole(CustomerPartyRoleEnum.INDIVIDUAL);
				log.trace("validado como INDIVIDUAL");
			} else {
				subscriber.setCustomerPartyRole(CustomerPartyRoleEnum.ORGANIZATION);
				log.trace("validado como ORGANIZATION");
			}
			log.trace("retornando informacion del subscriber... " +subscriber.toString());
			return subscriber;
			

		} catch (IntegrationException e) {
			throw IntegrationException.builder()
					.code(e.getCode())
					.message(e.getMessage())
					.technicalMessage(e.getMessage()).build();
	
		} catch (IOException e) {
			throw IntegrationException.builder()
					.code(90)
					.message("Hubo un problema al serializar respuesta obtenida del servicio para consulta de suscriptores en Huawei")
					.technicalMessage(e.getMessage()).build();
		}
	}

	/**
	 * Consumo por Axis por medio de MicroEis
	 * 
	 * @return querySubscriberResponseMessage
	 */
	@SuppressWarnings("deprecation")
	public Subscriber invokeGrpc(SubscriberTypeEnum subscriberType, String subscriberId) throws IntegrationException {
		try {
			log.trace("Ejecuntando Invocacion por GRPC MicroEis Axis...");
			log.trace("Ejecutando conexion GRPC... ");
			ManagedChannel channel = ManagedChannelBuilder.forAddress(ipMicroeis, portMicroeis).usePlaintext(true).build();
			log.trace("Mostrando canal de conexion: "+channel.toString());
			log.trace("Ejecutando stub por XMLService... ");
			InvokeXMLServiceGrpc.InvokeXMLServiceBlockingStub stub = InvokeXMLServiceGrpc.newBlockingStub(channel);
			log.trace("Mostrando Stub: "+stub.toString());
			log.trace("Ejecutando entradas... ");
			Inputs input = Inputs.newBuilder().setKey("subscriberId").setValue(subscriberId).build();
			log.trace("Mostrando entradas: "+input.toString());
			log.trace("Validada informacion, generando response...");
			InvokeResponse response = stub.invokeXML(InvokeRequest.newBuilder()
					.setInformationService("Consulta.Telefono2").setSource("msaEis/axisd").addInputs(input).build());
			channel.shutdown();
			log.trace("Mostrando response..." + response.toString());
			
			Subscriber subscriber = null;

			log.trace("Verificando que la respuesta no sea null...");
			if (response != null) {
				log.trace("Verificando que el valor de entrada no sea null..");
				if (response.getResponseCount() > 0 && response.getResponse(0) != null) {
					subscriber = new Subscriber();
					Identification identification = new Identification();
					subscriber.setIdentification(identification);
					String lista[] = response.getResponse(0).split(",");
					log.trace("Verificado = "+lista.toString());
					log.debug("Encontrado en MicroEis");

					for (int i = 0; i < lista.length; i++) {
						String key = lista[i].split("=")[0];
						String value = lista[i].split("=")[1];
						subscriber.setSubscriptionId(subscriberId);
						switch (key.trim()) {
						case "{FIRSTNAME":
							subscriber.setName(value);
							break;
						case "LASTNAME":
							subscriber.setLastName(value);
							break;
						case "SUBSCRIPTIONID":
							subscriber.setSubscriptionId(value);
							break;
						case "STATUS":
							subscriber.setStatus(value);
							break;
						case "SUBSCRIPTIONTYPE":
							subscriber.setSubscriptionType(SubscriptionTypeEnum.fromValue(value));
							break;
						case "CUSTOMERPARTYROLE":
							subscriber.setCustomerPartyRole(CustomerPartyRoleEnum.fromValue(value));
							break;
						case "IDENTIFICATION":
							identification.setValue(value);
							break;
						case "IDENTIFICATIONTYPE":
							identification.setType(TypeEnum.fromValue(value));
							break;

						default:
							break;
						}

					}
					
				}else{
					log.debug("Subscriptor no existe en axis..");
					
			}}
			log.trace("retornando informacion del subscriber de GRPC: "+subscriber.toString());
			return subscriber;
			
		} catch (StatusRuntimeException  e) {
			log.debug("Hubo un problema al consumir el servicio para consultar suscriptores en AXIS. Por favor revisar MicroEIS");
			throw IntegrationException.builder().code(30).message(
					"Hubo un problema al consumir el servicio para consultar suscriptores en AXIS. Por favor revisar MicroEIS")
					.technicalMessage(e.getMessage()).build();
			
		}catch (Exception e) {
			log.info("SUBSCRIPTOR NO EXISTE");
			throw IntegrationException.builder()
			.code(Constants.CODE_NOT_FOUND)
			.message(Constants.MESSAGE_NOT_FOUND)
			.technicalMessage(e.getMessage()).build();
			
		}

	}
		
		
	/**
	 * Escribe por medio de un Post en ValidateSubscriber en HW en Json para validar
	 * si existe el Subscriber
	 * 
	 * @return respuesta
	 */
	private String invokePost(String url, String request) throws IntegrationException {
		try {
			log.trace("Ejecutando metodo Post InquiryONE..");
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);

			HttpEntity<String> requestEntity = new HttpEntity<>(request, headers);
			ResponseEntity<String> respuesta = restTemplate.postForEntity(url, requestEntity, String.class);
			log.trace("Validando Post.. Mostrando respuesta: " + respuesta.toString());
			return respuesta.getBody();

		} catch (HttpClientErrorException | HttpServerErrorException | ResourceAccessException e) {
			String message;
			int code;
			if (e instanceof HttpClientErrorException) {
				code = 11;
				message = "Error al consumir el servicio Post InquiryOne (HttpClientErrorException)";
				log.error("Error al consumir el servicio Post InquiryOne (HttpClientErrorException)");
			} else if (e instanceof HttpServerErrorException) {
				code = 12;
				message = "Error al consumir el servicio Post InquiryOne (HttpServerErrorException)";
				log.error("Error al consumir el servicio Post InquiryOne (HttpServerErrorException)");
			} else if (e instanceof ResourceAccessException) {
				code = 13;
				message = "Error al consumir el servicio Post InquiryOne (ResourceAccessException)";
				log.error("Error al consumir el servicio Post InquiryOne (ResourceAccessException)");
			} else {
				code = 10;
				message = "Error al consumir el servicio Post InquiryOne";
				log.error("Error al consumir el servicio Post InquiryOne");
			}
			throw IntegrationException.builder().code(code).message(message).technicalMessage(e.getMessage()).build();
		}

	}

	/**
	 * Validado por medio de un Boleano en el invokePost, trae los datos del
	 * Subscriber al body
	 * 
	 * @return respuesta
	 */
	private String invokeGet(String url, Map<String, String> params, Map<String, String> cabeceras) throws IntegrationException {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		log.trace("Ejecutando metodo GET InquiryHW..");
		try {

			for (String key : cabeceras.keySet()) {
				String value = cabeceras.get(key);
				headers.set(key, value);
			}
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
			for (String key : params.keySet()) {
				String value = params.get(key);
				builder.queryParam(key, value);
			}
			HttpEntity<String> requestEntity = new HttpEntity<>(headers);
			ResponseEntity<String> respuesta = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET,
					requestEntity, String.class);
			log.trace("Validando GET.. Mostrando respuesta: " + respuesta.toString());
			return respuesta.getBody();

		} catch (HttpClientErrorException | HttpServerErrorException | ResourceAccessException e) {
			String message;
			int code;
			if (e instanceof HttpClientErrorException) {
				code = 21;
				message = "Error al consumir el servicio Get InquiryHwr (HttpClientErrorException)";
				log.error("Error al consumir el servicio Get InquiryHwr (HttpClientErrorException)");
				
			} else if (e instanceof HttpServerErrorException) {
				code = 22;
				message = "Error al consumir el servicio Get InquiryHwr (HttpServerErrorException)";
				log.error("Error al consumir el servicio Get InquiryHwr (HttpServerErrorException)");
				
			} else if (e instanceof ResourceAccessException) {
				code = 23;
				message = "Error al consumir el servicio Get InquiryHwr (ResourceAccessException)";
				log.error("Error al consumir el servicio Get InquiryHwr (ResourceAccessException)");
				
			} else {
				code = 20;
				message = "Error general en el servicio Get InquiryHwr";
				log.error("Error al consumir el servicio Get InquiryHwr");
			}
			throw IntegrationException.builder().code(code).message(message).technicalMessage(e.getMessage()).build();
		}

	}

}
