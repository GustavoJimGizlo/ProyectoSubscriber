package com.conecel.claro.controllers.dto;

import java.io.Serializable;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Entidad canónica que expresa metada de la ejecución de un servicio web
 */
@ApiModel(description = "Entidad canónica que expresa metada de la ejecución de un servicio web")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-03-22T12:39:21.861-05:00")

public class CommonHeaderRequest   implements Serializable {
	private static final long serialVersionUID = 227537383960381927L;
  @JsonProperty("operationInfo")
  private OperationInfo operationInfo = null;

  public CommonHeaderRequest operationInfo(OperationInfo operationInfo) {
    this.operationInfo = operationInfo;
    return this;
  }

  /**
   * Información de la ejecución de la operación
   * @return operationInfo
  **/
  @ApiModelProperty(required = true, value = "Información de la ejecución de la operación")
  @NotNull

  @Valid

  public OperationInfo getOperationInfo() {
    return operationInfo;
  }

  public void setOperationInfo(OperationInfo operationInfo) {
    this.operationInfo = operationInfo;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CommonHeaderRequest commonHeaderRequest = (CommonHeaderRequest) o;
    return Objects.equals(this.operationInfo, commonHeaderRequest.operationInfo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(operationInfo);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CommonHeaderRequest {\n");
    
    sb.append("    operationInfo: ").append(toIndentedString(operationInfo)).append("\n");
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

