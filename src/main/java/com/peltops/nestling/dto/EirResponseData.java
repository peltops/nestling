package com.peltops.nestling.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.peltops.nestling.entity.EquipmentStatus;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * EirResponseData
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-02-09T14:50:16.091738+01:00[Europe/Berlin]")

public class EirResponseData   {
  @JsonProperty("status")
  private EquipmentStatus status;

  public EirResponseData status(EquipmentStatus status) {
    this.status = status;
    return this;
  }

  /**
   * Get status
   * @return status
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public EquipmentStatus getStatus() {
    return status;
  }

  public void setStatus(EquipmentStatus status) {
    this.status = status;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EirResponseData eirResponseData = (EirResponseData) o;
    return Objects.equals(this.status, eirResponseData.status);
  }

  @Override
  public int hashCode() {
    return Objects.hash(status);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EirResponseData {\n");
    
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

