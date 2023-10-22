package com.invictus.app.api.service;

import com.invictus.app.api.dto.participant.ParticipantRequestDto;
import com.invictus.app.api.dto.participant.ParticipantResponseDto;
import com.invictus.app.api.dto.participant.ParticipantSaveRequestDto;
import com.invictus.app.api.entity.GroupRegistrationEntity;
import com.invictus.app.api.handler.models.NotFoundExceptionCustom;
import com.invictus.app.api.handler.models.RuleExceptionCustom;
import com.invictus.app.api.mapstruct.GroupMapper;
import com.invictus.app.api.mapstruct.ParticipantMapper;
import com.invictus.app.api.repository.GroupRepository;
import com.invictus.app.api.repository.ParticipantRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class ParticipantService {

    private final ParticipantRepository participantRepository;
    private final GroupRegistrationService groupRegistrationService;
    private final GroupRepository groupRepository;
    private final ParticipantMapper participantMapper;
    private final GroupMapper groupMapper;

    public ParticipantService(ParticipantRepository participantRepository, GroupRegistrationService groupRegistrationService, GroupRepository groupRepository, ParticipantMapper participantMapper, GroupMapper groupMapper) {
        this.participantRepository = participantRepository;
        this.groupRegistrationService = groupRegistrationService;
        this.groupRepository = groupRepository;
        this.participantMapper = participantMapper;
        this.groupMapper = groupMapper;
    }

    @Transactional
    public ParticipantResponseDto save(ParticipantSaveRequestDto requestDto) {
        var participantEntity = participantMapper.toEntity(requestDto);

        if(Objects.isNull(requestDto.getGroup()))
            return participantMapper.toResponse(participantRepository.save(participantEntity));

        var groupEntity = groupMapper.toEntity(requestDto.getGroup());
        var groupRegistration = groupRegistrationService.save(participantEntity, groupEntity);
        return participantMapper.toResponse(groupRegistration.getParticipant());
    }

    public List<ParticipantResponseDto> findAll() {
        var participants = participantRepository.findAll();
        return participants.stream().map(participantMapper::toResponse).toList();
    }

    public ParticipantResponseDto findById(UUID id) {
        var participantEntity = participantRepository.findById(id);
        if(participantEntity.isEmpty())
            throw new NotFoundExceptionCustom(String.format("Participant id=%s not found!", id));
        return participantMapper.toResponse(participantEntity.get());
    }

    @Transactional
    public void joinGroup(ParticipantRequestDto requestDto) {
        var groupEntityList = groupRepository.findAllById(requestDto.getGroupIds());

        if (groupEntityList.isEmpty())
            throw new NotFoundExceptionCustom("Groups with ids not found");

        var participantEntity = participantRepository.findById(requestDto.getId());

        if(participantEntity.isEmpty())
            throw new NotFoundExceptionCustom(String.format("Participant id=%s not found!", requestDto.getId()));

        groupEntityList.forEach(group -> {
            var isParticipantRegistered = group.getGroupRegistrations().stream().map(GroupRegistrationEntity::getParticipant)
                    .anyMatch(participantId -> participantId.getId().equals(requestDto.getId()));

            if(isParticipantRegistered) throw new RuleExceptionCustom("Participant with id=%s already exists in this group");
            groupRegistrationService.save(participantEntity.get(), group);
        });
    }
}
