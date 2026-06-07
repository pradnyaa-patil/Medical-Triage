package com.triage.medicaltriage.service;

import com.triage.medicaltriage.model.ChatMessage;
import com.triage.medicaltriage.model.ChatRequest;
import com.triage.medicaltriage.model.ChatResponse;

import java.util.List;

public interface ChatService {

    ChatResponse processMessage(ChatRequest request);

    List<ChatMessage> getHistory(String patientId);
}
