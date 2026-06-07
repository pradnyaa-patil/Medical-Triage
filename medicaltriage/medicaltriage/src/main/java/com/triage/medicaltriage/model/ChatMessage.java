package com.triage.medicaltriage.model;

import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ChatMessage {
    private Long id;

    private String sender;

    private String message;

    private String severity;

    private String patientId;

    private LocalDateTime createdAt;
}
