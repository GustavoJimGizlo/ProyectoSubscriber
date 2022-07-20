package com.conecel.claro.controllers.dto;

import java.io.Serializable;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.*;

/**
 * Información de la ejecución de la operación
 */
@ApiModel(description = "Información de la ejecución de la operación")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-03-22T12:39:21.861-05:00")

public class OperationInfo implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = -1852404717348893525L;
  @JsonProperty("operationId")
  private String operationId = null;

  @JsonProperty("transactionDate")
  private String transactionDate = null;

  @JsonProperty("transactionId")
  private String transactionId = null;

  public OperationInfo operationId(String operationId) {
    this.operationId = operationId;
    return this;
  }

  /**
   * ID de la operación que ejecutó el servicio
   * @return operationId
  **/
  @ApiModelProperty(required = true, value = "ID de la operación que ejecutó el servicio")
  @NotNull


  public String getOperationId() {
    return operationId;
  }

  public void setOperationId(String operationId) {
    this.operationId = operationId;
  }

  public OperationInfo transactionDate(String transactionDate) {
    this.transactionDate = transactionDate;
    return this;
  }

  /**
   * Fecha en la que se ejecutó la transacción
   * @return transactionDate
  **/
  @ApiModelProperty(required = true, value = "Fecha en la que se ejecutó la transacción")
  @NotNull


  public String getTransactionDate() {
    return transactionDate;
  }

  public void setTransactionDate(String transactionDate) {
    this.transactionDate = transactionDate;
  }

  public OperationInfo transactionId(String transactionId) {
    this.transactionId = transactionId;
    return this;
  }

  /**
   * ID unívoco de la transacción que se ejecuta. Esto por motivos de trazabilidad
   * @return transactionId
  **/
  @ApiModelProperty(required = true, value = "ID unívoco de la transacción que se ejecuta. Esto por motivos de trazabilidad")
  @NotNull


  public String getTransactionId() {
    return transactionId;
  }

  public void setTransactionId(String transactionId) {
    this.transactionId = transactionId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OperationInfo operationInfo = (OperationInfo) o;
    return Objects.equals(this.operationId, operationInfo.operationId) &&
        Objects.equals(this.transactionDate, operationInfo.transactionDate) &&
        Objects.equals(this.transactionId, operationInfo.transactionId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(operationId, transactionDate, transactionId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OperationInfo {\n");
    
    sb.append("    operationId: ").append(toIndentedString(operationId)).append("\n");
    sb.append("    transactionDate: ").append(toIndentedString(transactionDate)).append("\n");
    sb.append("    transactionId: ").append(toIndentedString(transactionId)).append("\n");
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

