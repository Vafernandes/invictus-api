package com.invictus.app.api.dto.group;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class GroupResponseDto {

    private UUID id;
    private String name;
    private int goalDays;
//    private List<GroupRegistrationEntity> groupRegistrations;
    private Instant creationDate;
    private Instant updatedDate;

}
