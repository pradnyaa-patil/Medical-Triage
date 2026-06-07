package com.triage.medicaltriage.model;

import lombok.Data;

@Data
public class ChatResponse {
    private String reply;

    private String severity;
}
