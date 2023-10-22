package com.invictus.app.api.service;

import com.invictus.app.api.dto.group.GroupRequestDto;
import com.invictus.app.api.dto.group.GroupResponseDto;
import com.invictus.app.api.handler.models.NotFoundExceptionCustom;
import com.invictus.app.api.mapstruct.GroupMapper;
import com.invictus.app.api.repository.GroupRepository;
import com.invictus.app.api.repository.ParticipantRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GroupService {

    private final GroupRepository groupRepository;
    private final ParticipantRepository participantRepository;
    private final GroupRegistrationService groupRegistrationService;
    private final GroupMapper groupMapper;

    public GroupService(GroupRepository groupRepository, ParticipantRepository participantRepository, GroupRegistrationService groupRegistrationService, GroupMapper groupMapper) {
        this.groupRepository = groupRepository;
        this.participantRepository = participantRepository;
        this.groupRegistrationService = groupRegistrationService;
        this.groupMapper = groupMapper;
    }

    @Transactional
    public GroupResponseDto save(GroupRequestDto requestDto) {
        var groupEntity = groupMapper.toEntity(requestDto);

        var participantEntity = participantRepository.findById(requestDto.getParticipantId());

        if(participantEntity.isEmpty())
            throw new NotFoundExceptionCustom(String.format("Participant id=%s not found!", requestDto.getParticipantId()));

        var groupRegistration = groupRegistrationService.save(participantEntity.get(), groupEntity);
        return groupMapper.toResponse(groupRegistration.getGroup());
    }

    public List<GroupResponseDto> findAll() {
        var groupEntityList = groupRepository.findAll();
        return groupEntityList.stream().map(groupMapper::toResponse).toList();
    }
}
