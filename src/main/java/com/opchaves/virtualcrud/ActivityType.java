package com.opchaves.virtualcrud;

import java.util.Locale;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ActivityType {
  INCOME,
  EXPENSE;

  /**
   * Serialises to and from lower case for jackson.
   *
   * @return lower case ActivityType name.
   */
  @JsonValue
  public String toLower() {
    return this.toString().toLowerCase(Locale.ENGLISH);
  }
}
