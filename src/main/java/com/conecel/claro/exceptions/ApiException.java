package com.conecel.claro.exceptions;

import lombok.Getter;
import lombok.Setter;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-03-22T12:39:21.861-05:00")

@Getter	
@Setter

public class ApiException extends Exception{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int code;
    
    
    public ApiException (int code, String msg, String technicalMesage) {
        super(msg);
        this.code = code;
        
    }
    
   
 }



