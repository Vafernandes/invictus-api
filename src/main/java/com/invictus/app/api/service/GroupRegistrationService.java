package com.invictus.app.api.service;

import com.invictus.app.api.entity.GroupEntity;
import com.invictus.app.api.entity.GroupRegistrationEntity;
import com.invictus.app.api.entity.ParticipantEntity;
import com.invictus.app.api.repository.GroupRegisterRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
public class GroupRegistrationService {

    private final GroupRegisterRepository groupRegisterRepository;

    public GroupRegistrationService(GroupRegisterRepository groupRegisterRepository) {
        this.groupRegisterRepository = groupRegisterRepository;
    }

    @Transactional
    public GroupRegistrationEntity save(ParticipantEntity participantEntity, GroupEntity groupEntity) {
        var groupRegisterEntityList = groupRegisterRepository.findByGroupId(groupEntity.getId());
        var owner = groupRegisterEntityList.isEmpty();

        var groupRegistration = GroupRegistrationEntity.builder()
                .participant(participantEntity)
                .group(groupEntity)
                .registrationDate(Instant.now())
                .owner(owner)
                .updatedDate(Instant.now())
                .build();

        return groupRegisterRepository.save(groupRegistration);
    }
}
