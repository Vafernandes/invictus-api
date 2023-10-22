package com.invictus.app.api.service;

import com.invictus.app.api.dto.participant.ParticipantRequestDto;
import com.invictus.app.api.dto.participant.ParticipantResponseDto;
import com.invictus.app.api.dto.participant.ParticipantSaveRequestDto;
import com.invictus.app.api.entity.GroupEntity;
import com.invictus.app.api.handler.models.NotFoundExceptionCustom;
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
            participantMapper.toResponse(participantRepository.save(participantEntity));

        var groupEntity = groupMapper.toEntity(requestDto.getGroup());
        var groupRegistration = groupRegistrationService.save(participantEntity, groupEntity);
        return participantMapper.toResponse(groupRegistration.getParticipant());
    }

    public List<GroupEntity> registerGroup(List<UUID> groupsUuids) {
        var groups = groupRepository.findAllById(groupsUuids);

        if (groups.isEmpty()) {
            throw new NotFoundExceptionCustom("Group not found");
        }

        return groups;
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

    public ParticipantResponseDto update(ParticipantRequestDto requestDto) {
        var entity = participantMapper.toEntity(requestDto);

//        if (Objects.nonNull(requestDto.getGroupIds())) {
//            var groups = registerGroup(requestDto.getGroupIds());
//
//            entity.setCreationDate(Instant.now());
//            entity.setUpdatedDate(Instant.now());
//            var participant = participantRepository.save(entity);
//
//            List<GroupRegistrationEntity> registerList = groups.stream().map(group ->
//                    GroupRegistrationEntity.builder()
//                            .group(group)
//                            .participant(participant)
//                            .registrationDate(Instant.now())
//                            .updatedDate(Instant.now())
//                            .build()
//            ).toList();
//
//            var registrationEntityList = groupRegisterRepository.saveAll(registerList);
//
//            participant.setGroupRegistrationEntities(registrationEntityList);
//            return participantMapper.toResponse(participant);
//        }

        return participantMapper.toResponse(participantRepository.save(entity));
    }
}
