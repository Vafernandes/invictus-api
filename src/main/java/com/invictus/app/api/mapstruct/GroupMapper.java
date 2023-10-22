package com.invictus.app.api.mapstruct;

import com.invictus.app.api.dto.group.GroupRequestDto;
import com.invictus.app.api.dto.group.GroupResponseDto;
import com.invictus.app.api.entity.GroupEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GroupMapper {

    GroupEntity toEntity(GroupRequestDto groupRequestDto);
    GroupResponseDto toResponse(GroupEntity group);
}
