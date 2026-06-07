package com.triage.medicaltriage.service;

import com.triage.medicaltriage.model.ChatMessage;
import com.triage.medicaltriage.model.Patient;

import java.util.List;

public interface PromptBuilderService {
    String buildPrompt(Patient patient, List<ChatMessage> history, String currentMessage);
}
