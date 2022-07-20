package com.conecel.claro.controllers.dto;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Entidad canónica la cual transmite la información de consulta de un
 * subscriptor.
 */
@ApiModel(description = "Entidad canónica la cual transmite la información de consulta de un subscriptor.")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-03-22T12:39:21.861-05:00")
@JsonInclude(value = Include.NON_NULL)
public class QuerySubscriberResponseMessage implements Serializable {

	private static final long serialVersionUID = -6936127015696354989L;

	@JsonProperty("commonHeaderRequest")
	private CommonHeaderRequest commonHeaderRequest = null;

	@JsonProperty("subscriberInfo")
	private SubscriberInfo subscriberInfo = null;

	//custom constructor
	public QuerySubscriberResponseMessage(Subscriber subscriber, String transactionId) {
		String pattern2 = "yyyy-MM-dd'T'HH:mm:ss.SSS";
		SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(pattern2);
		
		OperationInfo oper = new OperationInfo();
		oper.setTransactionDate(simpleDateFormat2.format(Calendar.getInstance().getTime()));
		oper.setOperationId(Constants.OPERATIONID);
		oper.setTransactionId(transactionId);
		
		CommonHeaderRequest commonHeaderRequest = new CommonHeaderRequest();
		commonHeaderRequest.setOperationInfo(oper);

		if(subscriber == null)			
			this.subscriberInfo(new SubscriberInfo(Constants.CODE_NOT_FOUND, Constants.MESSAGE_NOT_FOUND, subscriber));
		else 
			this.subscriberInfo(new SubscriberInfo(Constants.CODE, Constants.MESSAGE, subscriber));

		this.commonHeaderRequest(commonHeaderRequest);
		
	}
	
	//custom constructor
	public QuerySubscriberResponseMessage(Integer code, String message, String transactionId) {
		String pattern2 = "yyyy-MM-dd'T'HH:mm:ss.SSS";
		SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(pattern2);
		
		OperationInfo oper = new OperationInfo();
		oper.setTransactionDate(simpleDateFormat2.format(Calendar.getInstance().getTime()));
		oper.setOperationId(Constants.OPERATIONID);
		oper.setTransactionId(transactionId);
		
		CommonHeaderRequest commonHeaderRequest = new CommonHeaderRequest();
		commonHeaderRequest.setOperationInfo(oper);
		
		
		SubscriberInfo info = new SubscriberInfo(code, message, null);
		
		this.commonHeaderRequest(commonHeaderRequest);
		this.subscriberInfo(info);
	}
	
	public QuerySubscriberResponseMessage commonHeaderRequest(CommonHeaderRequest commonHeaderRequest) {
		this.commonHeaderRequest = commonHeaderRequest;
		return this;
	}

	/**
	 * Entidad canónica que contiene metada de ejecución del servicio
	 * 
	 * @return commonHeaderRequest
	 **/
	@ApiModelProperty(required = true, value = "Entidad canónica que contiene metada de ejecución del servicio")
	@NotNull

	@Valid
	public CommonHeaderRequest getCommonHeaderRequest() {
		return commonHeaderRequest;
	}

	public void setCommonHeaderRequest(CommonHeaderRequest commonHeaderRequest) {
		this.commonHeaderRequest = commonHeaderRequest;
	}

	public QuerySubscriberResponseMessage subscriberInfo(SubscriberInfo subscriberInfo) {
		this.subscriberInfo = subscriberInfo;
		return this;
	}

	/**
	 * Get subscriberInfo
	 * 
	 * @return subscriberInfo
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

	@Valid

	public SubscriberInfo getSubscriberInfo() {
		return subscriberInfo;
	}

	public void setSubscriberInfo(SubscriberInfo subscriberInfo) {
		this.subscriberInfo = subscriberInfo;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		QuerySubscriberResponseMessage querySubscriberResponseMessage = (QuerySubscriberResponseMessage) o;
		return Objects.equals(this.commonHeaderRequest, querySubscriberResponseMessage.commonHeaderRequest)
				&& Objects.equals(this.subscriberInfo, querySubscriberResponseMessage.subscriberInfo);
	}

	@Override
	public int hashCode() {
		return Objects.hash(commonHeaderRequest, subscriberInfo);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class QuerySubscriberResponseMessage {\n");

		sb.append("    commonHeaderRequest: ").append(toIndentedString(commonHeaderRequest)).append("\n");
		sb.append("    subscriberInfo: ").append(toIndentedString(subscriberInfo)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
