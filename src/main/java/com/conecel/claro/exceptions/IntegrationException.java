package com.conecel.claro.exceptions;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-03-22T12:39:21.861-05:00")

@Data
@Builder
@EqualsAndHashCode(callSuper=false)
public class IntegrationException extends Exception {
	private static final long serialVersionUID = -3057296594421049351L;
	private int code;
	private String message;
	private String technicalMessage;
	
	public IntegrationException (int code, String msg, String technicalMessage) {
        super();
        this.message = msg;
        this.code = code;
        this.technicalMessage = technicalMessage;
    }
	
	public IntegrationException() {}
}

