package com.opchaves.virtualcrud;

import java.time.LocalDateTime;

import org.bson.types.ObjectId;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;

@MongoEntity(collection = "activities")
public class Activity extends PanacheMongoEntity {
  public String name;
  public String description;
  public Double price;
  public Boolean paid;
  public ActivityType type;
  public String category;
  public ObjectId userId;
  public LocalDateTime handledAt;
  public LocalDateTime createdAt;
  public LocalDateTime updatedAt;

  public Activity() {
  }
}
