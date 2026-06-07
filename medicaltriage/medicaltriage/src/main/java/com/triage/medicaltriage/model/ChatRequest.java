package com.triage.medicaltriage.model;

import lombok.Data;

@Data
public class ChatRequest {
    private String message;

    private String patientId;
}
