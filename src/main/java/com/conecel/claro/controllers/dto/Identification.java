package com.conecel.claro.controllers.dto;

import java.io.Serializable;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.*;

/**
 * Contiene la información de la identificación del subscriptor.
 */
@ApiModel(description = "Contiene la información de la identificación del subscriptor.")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-03-22T12:39:21.861-05:00")

public class Identification  implements Serializable {
  /**
	 * 
	 */
	private static final long serialVersionUID = -5102334511731689196L;

/**
   * Tipo de identificación del subscriptor
   */
  public enum TypeEnum {
    CED("CED"),
    
    PAS("PAS"),
    
    RUC("RUC"),

	CUSTOMERID("CUSTOMERID");
    private String value;

    TypeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static TypeEnum fromValue(String text) {
      for (TypeEnum b : TypeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("type")
  private TypeEnum type = null;

  @JsonProperty("value")
  private String value = null;

  public Identification type(TypeEnum type) {
    this.type = type;
    return this;
  }

  /**
   * Tipo de identificación del subscriptor
   * @return type
  **/
  @ApiModelProperty(required = true, value = "Tipo de identificación del subscriptor")
  @NotNull


  public TypeEnum getType() {
    return type;
  }

  public void setType(TypeEnum type) {
    this.type = type;
  }

  public Identification value(String value) {
    this.value = value;
    return this;
  }

  /**
   * Código, cadena o valor unívoca de la identificación del subscriptor.
   * @return value
  **/
  @ApiModelProperty(required = true, value = "Código, cadena o valor unívoca de la identificación del subscriptor.")
  @NotNull


  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Identification identification = (Identification) o;
    return Objects.equals(this.type, identification.type) &&
        Objects.equals(this.value, identification.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, value);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Identification {\n");
    
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    value: ").append(toIndentedString(value)).append("\n");
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

