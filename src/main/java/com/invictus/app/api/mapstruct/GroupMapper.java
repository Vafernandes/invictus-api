package com.invictus.app.api.mapstruct;

import com.invictus.app.api.dto.group.GroupRequestDto;
import com.invictus.app.api.dto.group.GroupResponseDto;
import com.invictus.app.api.entity.GroupEntity;
import com.invictus.app.api.entity.GroupRegistrationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface GroupMapper {

    @Mapping(target = "creationDate", defaultExpression = "java(java.time.Instant.now())")
    @Mapping(target = "updatedDate", defaultExpression = "java(java.time.Instant.now())")
    GroupEntity toEntity(GroupRequestDto groupRequestDto);

    @Mapping(target = "participantIds", source = "entity.groupRegistrations")
    GroupResponseDto toResponse(GroupEntity entity);

    default UUID map(GroupRegistrationEntity entity) {
        return entity.getGroup().getId();
    }

}
