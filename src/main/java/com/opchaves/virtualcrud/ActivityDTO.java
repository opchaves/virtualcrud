package com.opchaves.virtualcrud;

import java.time.LocalDateTime;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(name = "ActivityDTO", description = "Activity input data")
public class ActivityDTO {
  @Schema(example = "507f1f77bcf86cd799439011", description = "Activity Object Id")
  public String id;

  @Schema(required = true)
  @NotBlank(message = "{Activity.name.required}")
  public String name;

  @Schema
  public String description;

  @Schema(defaultValue = "0.0", minimum = "0.01")
  @DecimalMin(value = "0.01", message = "{Activity.price.min}")
  public Double price;

  @Schema(defaultValue = "false")
  public Boolean paid;

  @Schema(required = true, enumeration = { "income", "expense" })
  @NotNull(message = "{Activity.type.required}")
  public ActivityType type;

  @Schema(required = true)
  @NotBlank(message = "{Activity.category.required}")
  public String category;

  @Schema(example = "507f1f77bcf86cd799439011", description = "User Object Id")
  public String userId;

  @Schema(name = "handledAt")
  public LocalDateTime handledAt;

  @Schema
  public LocalDateTime createdAt;

  @Schema
  public LocalDateTime updatedAt;

  public ActivityDTO() {
  }
}
