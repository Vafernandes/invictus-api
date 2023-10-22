package com.invictus.app.api.mapstruct;

import com.invictus.app.api.dto.participant.ParticipantRequestDto;
import com.invictus.app.api.dto.participant.ParticipantResponseDto;
import com.invictus.app.api.dto.participant.ParticipantSaveRequestDto;
import com.invictus.app.api.entity.GroupRegistrationEntity;
import com.invictus.app.api.entity.ParticipantEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface ParticipantMapper {

    ParticipantEntity toEntity(ParticipantRequestDto requestDto);

    @Mapping(target = "creationDate", defaultExpression = "java(java.time.Instant.now())")
    @Mapping(target = "updatedDate", defaultExpression = "java(java.time.Instant.now())")
    ParticipantEntity toEntity(ParticipantSaveRequestDto requestDto);

    @Mapping(target = "groupIds", source = "entity.groupRegistrationEntities")
    ParticipantResponseDto toResponse(ParticipantEntity entity);
    List<UUID> toResponseGroupList(List<GroupRegistrationEntity> groupRegistrationEntities);
    default UUID map(GroupRegistrationEntity entity) {
        return entity.getGroup().getId();
    }
}
