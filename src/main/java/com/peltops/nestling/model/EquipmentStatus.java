package com.peltops.nestling.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonValue;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Gets or Sets EquipmentStatus
 */
public enum EquipmentStatus {
  
  WHITELISTED("WHITELISTED"),
  
  BLACKLISTED("BLACKLISTED"),
  
  GREYLISTED("GREYLISTED");

  private String value;

  EquipmentStatus(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static EquipmentStatus fromValue(String value) {
    for (EquipmentStatus b : EquipmentStatus.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}

