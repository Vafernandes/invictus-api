package com.invictus.app.api.dto.group;

import lombok.Data;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Data
public class GroupResponseDto {

    private UUID id;
    private String name;
    private int goalDays;
    private List<UUID> participantIds;
    private Instant creationDate;
    private Instant updatedDate;

}
