package com.invictus.app.api.dto.group;

import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class GroupRequestDto {

    private String name;
    private int goalDays;
    private String periodOfChallenge;
    private UUID participantId;
    private Instant creationDate;
    private Instant updatedDate;
}
