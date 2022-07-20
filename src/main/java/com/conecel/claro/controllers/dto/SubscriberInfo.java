package com.conecel.claro.controllers.dto;

import java.io.Serializable;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Contiene información básica del subscriptor
 */

@ApiModel(description = "Contiene información básica del subscriptor")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-03-22T12:39:21.861-05:00")
@JsonInclude(value = Include.NON_NULL)
@Getter
@Setter
@AllArgsConstructor
public class SubscriberInfo implements Serializable  {
   /**
	 * 
	 */
	private static final long serialVersionUID = -1680722065750177808L; 
	 /**
	   * Código de ejecución del servicio
	   * @return code
	  **/
  @JsonProperty("code")
  @ApiModelProperty(required = true, value = "Código de ejecución del servicio")
  @NotNull
  private Integer code = null;
  
  public SubscriberInfo message(String message) {
	    this.message = message;
	    return this;
	  }

  /**
   * Mensaje que indica la ejecución de la consulta.
   * @return message
  **/
  @JsonProperty("message")
  @ApiModelProperty(required = true, value = "Mensaje que indica la ejecución de la consulta.")
  @NotNull
  private String message = null;
  

  public SubscriberInfo subscriber(Subscriber subscriber) {
    this.subscriber = subscriber;
    return this;
  }
  
  
  /**
   * Info básica del subscriptor
   * @return subscriber
  **/
  @JsonProperty("subscriber")
  @ApiModelProperty(required = true, value = "Info básica del subscriptor")
  @NotNull
  @Valid
  private Subscriber subscriber = null;

  public SubscriberInfo code(Integer code) {
    this.code = code;
    return this;
  }

 
  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SubscriberInfo subscriberInfo = (SubscriberInfo) o;
    return Objects.equals(this.code, subscriberInfo.code) &&
        Objects.equals(this.message, subscriberInfo.message) &&
        Objects.equals(this.subscriber, subscriberInfo.subscriber);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, message, subscriber);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SubscriberInfo {\n");
    
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("    subscriber: ").append(toIndentedString(subscriber)).append("\n");
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

