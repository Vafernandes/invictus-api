package com.invictus.app.api.dto.participant;

import com.invictus.app.api.dto.group.GroupRequestDto;
import lombok.Data;

import java.time.Instant;

@Data
public class ParticipantSaveRequestDto {
    private String name;
    private String email;
    private GroupRequestDto group;
    private Instant creationDate;
    private Instant updatedDate;
}
