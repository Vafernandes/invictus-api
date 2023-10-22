package com.invictus.app.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "group_registration")
public class GroupRegistrationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "participant_id")
    private ParticipantEntity participant;
    @ManyToOne
    @JoinColumn(name = "group_id")
    private GroupEntity group;

    private Instant registrationDate;
    private Instant updatedDate;

}
