package com.invictus.app.api.dto.participant;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class ParticipantRequestDto {
    private UUID id;
    private List<UUID> groupIds;
}
