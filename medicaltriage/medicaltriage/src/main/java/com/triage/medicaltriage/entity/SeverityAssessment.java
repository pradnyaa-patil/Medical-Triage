package com.triage.medicaltriage.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "severity_assessment")
@Data
public class SeverityAssessment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String patientId;

    private String severity;

    @Column(length = 2000)
    private String reason;

    @Column(length = 2000)
    private String symptoms;

    private LocalDateTime createdAt;
}
