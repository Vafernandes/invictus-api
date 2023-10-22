package com.invictus.app.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "rankings")
public class RankinEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private GroupRegistrationEntity groupRegistrationEntity;

    private boolean isGoalReached;
    private String periodOfChallenge;
    private Instant challengeStartDate;
    private Instant challengeEndDate;
}
