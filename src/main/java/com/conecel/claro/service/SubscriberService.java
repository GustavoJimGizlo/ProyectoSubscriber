package com.conecel.claro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.conecel.claro.controllers.dto.Subscriber;
import com.conecel.claro.controllers.dto.Subscriber.SubscriberTypeEnum;
import com.conecel.claro.exceptions.IntegrationException;
import com.conecel.claro.integration.IntegrationService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class SubscriberService {

	@Autowired
	private IntegrationService integrationService;

	@Cacheable(value = "querysubscriber", key = "{#p0 , #p1}", cacheManager = "cacheManager", unless = "#result == null")
	public Subscriber querySubscriberOrchestation(String subscriberType, String subscriberId) throws IntegrationException {
		log.debug("SubscriberTypeEnum recibido: " + subscriberType);
		SubscriberTypeEnum subscriberTypeEnum = this.validateSubscriberType(subscriberType);
		log.debug("SubscriberTypeEnum normalizado: " + subscriberTypeEnum);
		
		log.debug("Validando existencia del suscriptor en Huawei...");
		boolean validateSubscriber = integrationService.validateSubscriber(subscriberTypeEnum, subscriberId);
		log.debug("Suscriptor existe en Huawei: " + validateSubscriber);
		
		if (validateSubscriber)
			return integrationService
					.suscriberInquiryHW(subscriberTypeEnum, subscriberId);
		log.debug("Validando en GRPC..");
		return integrationService
					.invokeGrpc(subscriberTypeEnum, subscriberId);
		
	}
	
	private SubscriberTypeEnum validateSubscriberType(String subscriberType) {
		SubscriberTypeEnum subscriberTypeEnum = SubscriberTypeEnum.fromValue(subscriberType);
		if(subscriberTypeEnum == null)
			return SubscriberTypeEnum.SERVICENUMBER;
		return subscriberTypeEnum;
	}
}
