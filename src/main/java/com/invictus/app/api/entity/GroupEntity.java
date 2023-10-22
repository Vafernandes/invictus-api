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
@Builder
@Data
@Entity
@Table(name = "groups")
public class GroupEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(unique = true)
    private String name;
    private int goalDays;
    @OneToMany(mappedBy = "group", fetch = FetchType.EAGER)
    private List<GroupRegistrationEntity> groupRegistrations;
    private String periodOfChallenge;
    private Instant challengeStartDate;
    private Instant challengeEndDate;
    private Instant creationDate;
    private Instant updatedDate;
}
