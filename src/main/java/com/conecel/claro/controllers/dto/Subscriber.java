package com.conecel.claro.controllers.dto;

import java.io.Serializable;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Entidad que transmite la información básica del subscriptor
 */
@Service
@ApiModel(description = "Entidad que transmite la información básica del subscriptor")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-03-22T12:39:21.861-05:00")
public class Subscriber  implements Serializable {
  /**
	 * 
	 */
	private static final long serialVersionUID = 4632822177009132473L;
  @JsonProperty("subscriptionId")
  private String subscriptionId = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("lastName")
  private String lastName = null;

  @JsonProperty("identification")
  private Identification identification = null;

  @JsonProperty("status")
  private String status = null;
  

  public enum SubscriberTypeEnum {
	    SERVICENUMBER("SERVICENUMBER"),
	    IMSI("IMSI"),
	    IMEI("IMEI"),
	    ICCID("ICCID");
	    
	    private String value;

	  SubscriberTypeEnum(String value) {
	      this.value = value;
	    }

	    @Override
	    @JsonValue
	    public String toString() {
	      return String.valueOf(value);
	    }

	    @JsonCreator
	    public static SubscriberTypeEnum fromValue(String text) {
	      for (SubscriberTypeEnum b : SubscriberTypeEnum.values()) {
	        if (String.valueOf(b.value).equals(text)) {
	          return b;
	        }
	      }
	      return null;
	    }
	  }

  /**
   * Tipo de subscriptor.
   */
  public enum SubscriptionTypeEnum {
    PREPAID("Prepaid"),
    
    POSTPAID("Postpaid");

    private String value;

    SubscriptionTypeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static SubscriptionTypeEnum fromValue(String text) {
      for (SubscriptionTypeEnum b : SubscriptionTypeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("subscriptionType")
  private SubscriptionTypeEnum subscriptionType = null;

  /**
   * Indica el segmento de mercado en el cual se encuentra el subscriptor
   */
  public enum CustomerPartyRoleEnum {
    INDIVIDUAL("Individual"),
    
    ORGANIZATION("Organization");

    private String value;

    CustomerPartyRoleEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static CustomerPartyRoleEnum fromValue(String text) {
      for (CustomerPartyRoleEnum b : CustomerPartyRoleEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("customerPartyRole")
  private CustomerPartyRoleEnum customerPartyRole = null;

  public Subscriber subscriptionId(String subscriptionId) {
    this.subscriptionId = subscriptionId;
    return this;
  }

  /**
   * Código unívoco de la subscripción
   * @return subscriptionId
  **/
  @ApiModelProperty(required = true, value = "Código unívoco de la subscripción")
  @NotNull


  public String getSubscriptionId() {
    return subscriptionId;
  }

  public void setSubscriptionId(String subscriptionId) {
    this.subscriptionId = subscriptionId;
  }

  public Subscriber name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Nombre del subcriptor
   * @return name
  **/
  @ApiModelProperty(required = true, value = "Nombre del subcriptor")
  @NotNull


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Subscriber lastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  /**
   * Apellido del subscriptor
   * @return lastName
  **/
  @ApiModelProperty(required = true, value = "Apellido del subscriptor")
  @NotNull


  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Subscriber identification(Identification identification) {
    this.identification = identification;
    return this;
  }

  /**
   * Documento de identificación del subcriptor
   * @return identification
  **/
  @ApiModelProperty(required = true, value = "Documento de identificación del subcriptor")
  @NotNull

  @Valid

  public Identification getIdentification() {
    return identification;
  }

  public void setIdentification(Identification identification) {
    this.identification = identification;
  }

  public Subscriber status(String status) {
    this.status = status;
    return this;
  }

  /**
   * Indica el estado en el cual se encuentra el subscriptor.
   * @return status
  **/
  @ApiModelProperty(required = true, value = "Indica el estado en el cual se encuentra el subscriptor.")
  @NotNull


  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Subscriber subscriptionType(SubscriptionTypeEnum subscriptionType) {
    this.subscriptionType = subscriptionType;
    return this;
  }

  /**
   * Tipo de subscriptor.
   * @return subscriptionType
  **/
  @ApiModelProperty(required = true, value = "Tipo de subscriptor.")
  @NotNull


  public SubscriptionTypeEnum getSubscriptionType() {
    return subscriptionType;
  }

  public void setSubscriptionType(SubscriptionTypeEnum subscriptionType) {
    this.subscriptionType = subscriptionType;
  }

  public Subscriber customerPartyRole(CustomerPartyRoleEnum customerPartyRole) {
    this.customerPartyRole = customerPartyRole;
    return this;
  }

  /**
   * Indica el segmento de mercado en el cual se encuentra el subscriptor
   * @return customerPartyRole
  **/
  @ApiModelProperty(required = true, value = "Indica el segmento de mercado en el cual se encuentra el subscriptor")
  @NotNull


  public CustomerPartyRoleEnum getCustomerPartyRole() {
    return customerPartyRole;
  }

  public void setCustomerPartyRole(CustomerPartyRoleEnum customerPartyRole) {
    this.customerPartyRole = customerPartyRole;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Subscriber subscriber = (Subscriber) o;
    return Objects.equals(this.subscriptionId, subscriber.subscriptionId) &&
        Objects.equals(this.name, subscriber.name) &&
        Objects.equals(this.lastName, subscriber.lastName) &&
        Objects.equals(this.identification, subscriber.identification) &&
        Objects.equals(this.status, subscriber.status) &&
        Objects.equals(this.subscriptionType, subscriber.subscriptionType) &&
        Objects.equals(this.customerPartyRole, subscriber.customerPartyRole);
  }

  @Override
  public int hashCode() {
    return Objects.hash(subscriptionId, name, lastName, identification, status, subscriptionType, customerPartyRole);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Subscriber {\n");
    
    sb.append("    subscriptionId: ").append(toIndentedString(subscriptionId)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n");
    sb.append("    identification: ").append(toIndentedString(identification)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    subscriptionType: ").append(toIndentedString(subscriptionType)).append("\n");
    sb.append("    customerPartyRole: ").append(toIndentedString(customerPartyRole)).append("\n");
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

