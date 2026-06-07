package com.triage.medicaltriage.controller;

import com.triage.medicaltriage.model.ChatMessage;
import com.triage.medicaltriage.model.ChatRequest;
import com.triage.medicaltriage.model.ChatResponse;
import com.triage.medicaltriage.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    private ChatService chatService;

    @PostMapping("/message")
    public ChatResponse sendMessage(@RequestBody ChatRequest request) {

        return chatService.processMessage(request);
    }

    @GetMapping("/history/{patientId}")
    public List<ChatMessage> getHistory(@PathVariable String patientId) {
        List<ChatMessage> chatMessage = chatService.getHistory(patientId);
        return chatMessage;
    }

}
