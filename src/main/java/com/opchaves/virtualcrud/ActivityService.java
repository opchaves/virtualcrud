package com.opchaves.virtualcrud;

import java.time.LocalDateTime;
import java.util.List;

import org.bson.types.ObjectId;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ActivityService {
  private final ActivityMapper mapper;

  public ActivityService(ActivityMapper activityMapper) {
    this.mapper = activityMapper;
  }

  public List<ActivityDTO> listAll() {
    return Activity.<Activity>listAll().stream().map(mapper::toDTO).toList();
  }

  public List<ActivityDTO> listByUserId(ObjectId userId) {
    return Activity.<Activity>find("userId", userId).list().stream().map(mapper::toDTO).toList();
  }

  public ActivityDTO findById(ObjectId id) {
    return Activity.<Activity>findByIdOptional(id).map(mapper::toDTO).orElse(null);
  }

  public ActivityDTO findUserActivity(String userId, String activityId) {
    return Activity.<Activity>find("userId = ?1 and id = ?2", userId, activityId).firstResultOptional()
        .map(mapper::toDTO).orElse(null);
  }

  public ActivityDTO create(ActivityDTO activityDTO, ObjectId userId) {
    Activity activity = mapper.toEntity(activityDTO);
    activity.userId = userId;
    activity.createdAt = LocalDateTime.now();
    activity.updatedAt = LocalDateTime.now();
    activity.persist();
    return mapper.toDTO(activity);
  }

  public ActivityDTO update(ObjectId id, ActivityDTO input, ObjectId userId) {
    Activity activity = Activity.<Activity>find("userId = ?1 and id = ?2", userId, id).firstResult();

    if (activity == null) {
      return null;
    }

    activity.name = input.name;
    activity.description = input.description;
    activity.price = input.price;
    activity.paid = input.paid;
    activity.category = input.category;
    activity.handledAt = input.handledAt;
    activity.updatedAt = LocalDateTime.now();

    activity.persist();

    return mapper.toDTO(activity);
  }

  public boolean delete(ObjectId id, ObjectId userId) {
    return Activity.delete("userId = ?1 and id = ?2", userId, id) > 0;
  }
}
