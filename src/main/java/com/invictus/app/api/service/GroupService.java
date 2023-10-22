package com.invictus.app.api.service;

import com.invictus.app.api.dto.group.GroupRequestDto;
import com.invictus.app.api.dto.group.GroupResponseDto;
import com.invictus.app.api.entity.GroupRegistrationEntity;
import com.invictus.app.api.mapstruct.GroupMapper;
import com.invictus.app.api.repository.GroupRegisterRepository;
import com.invictus.app.api.repository.GroupRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
public class GroupService {

    private final GroupRepository groupRepository;
    private final GroupRegisterRepository groupRegisterRepository;
    private final GroupMapper groupMapper;

    public GroupService(GroupRepository groupRepository, GroupRegisterRepository groupRegisterRepository, GroupMapper groupMapper) {
        this.groupRepository = groupRepository;
        this.groupRegisterRepository = groupRegisterRepository;
        this.groupMapper = groupMapper;
    }

    @Transactional
    public GroupResponseDto save(GroupRequestDto requestDto) {
        var entity = groupMapper.toEntity(requestDto);
        entity.setCreationDate(Instant.now());
        entity.setUpdatedDate(Instant.now());

        return groupMapper.toResponse(groupRepository.save(entity));
    }

}
