package com.opchaves.virtualcrud;

import java.time.LocalDateTime;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "cdi", imports = { LocalDateTime.class })
public interface ActivityMapper {

  @Mapping(target = "id", expression = "java(a.id.toHexString())")
  @Mapping(target = "userId", expression = "java(a.userId.toHexString())")
  ActivityDTO toDTO(Activity a);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "userId", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  @Mapping(target = "handledAt", defaultExpression = "java(LocalDateTime.now())")
  Activity toEntity(ActivityDTO a);
}
