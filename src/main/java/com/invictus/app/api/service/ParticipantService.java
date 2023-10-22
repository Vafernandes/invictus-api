package com.invictus.app.api.service;

import com.invictus.app.api.dto.participant.ParticipantRequestDto;
import com.invictus.app.api.dto.participant.ParticipantResponseDto;
import com.invictus.app.api.entity.GroupEntity;
import com.invictus.app.api.entity.GroupRegistrationEntity;
import com.invictus.app.api.handler.models.NotFoundExceptionCustom;
import com.invictus.app.api.mapstruct.ParticipantMapper;
import com.invictus.app.api.repository.GroupRegisterRepository;
import com.invictus.app.api.repository.GroupRepository;
import com.invictus.app.api.repository.ParticipantRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ParticipantService {

    private final ParticipantRepository participantRepository;
    private final GroupRegisterRepository groupRegisterRepository;
    private final GroupRepository groupRepository;
    private final ParticipantMapper participantMapper;

    public ParticipantService(ParticipantRepository participantRepository, GroupRegisterRepository groupRegisterRepository, GroupRepository groupRepository, ParticipantMapper participantMapper) {
        this.participantRepository = participantRepository;
        this.groupRegisterRepository = groupRegisterRepository;
        this.groupRepository = groupRepository;
        this.participantMapper = participantMapper;
    }

    @Transactional
    public ParticipantResponseDto save(ParticipantRequestDto participantRequestDto) {
        var entity = participantMapper.toEntity(participantRequestDto);

        if (Objects.nonNull(participantRequestDto.getGroupIds())) {
            var groups = registerGroup(participantRequestDto.getGroupIds());

            entity.setCreationDate(Instant.now());
            entity.setUpdatedDate(Instant.now());
            var participant = participantRepository.save(entity);

            List<GroupRegistrationEntity> registerList = groups.stream().map(group ->
                    GroupRegistrationEntity.builder()
                            .group(group)
                            .participant(participant)
                            .registrationDate(Instant.now())
                            .updatedDate(Instant.now())
                            .build()
            ).toList();

            var registrationEntityList = groupRegisterRepository.saveAll(registerList);

            participant.setGroupRegistrationEntities(registrationEntityList);
            return participantMapper.toResponse(participant);
        }

        return participantMapper.toResponse(participantRepository.save(entity));
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
        return participantMapper.toResponse(participantRepository.findById(id).orElseThrow());
    }
}
