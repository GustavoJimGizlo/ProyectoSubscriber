package com.conecel.claro.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-03-22T12:39:21.861-05:00")

@Data
@EqualsAndHashCode(callSuper=false)
public class NotFoundException extends Exception {
	private static final long serialVersionUID = -4394545456016511546L;
	private int code;
	private String message;
    
    public NotFoundException (int code, String msg) {
        super(msg);
        this.code = code;
        this.message = msg;
    }
}
