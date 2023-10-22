package com.invictus.app.api.dto.groupRegistration;

import com.invictus.app.api.dto.group.GroupResponseDto;
import com.invictus.app.api.dto.participant.ParticipantResponseDto;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class GroupRegistrationResponseDto {
    private UUID id;
    private ParticipantResponseDto participant;
    private GroupResponseDto group;
    private Instant registrationDate;
    private Instant updatedDate;
}
