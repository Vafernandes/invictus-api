package com.invictus.app.api.dto.participant;

import lombok.Data;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Data
public class ParticipantResponseDto {
    private UUID id;
    private String name;
    private List<UUID> groupIds;
    private Instant creationDate;
    private Instant updatedDate;
}
