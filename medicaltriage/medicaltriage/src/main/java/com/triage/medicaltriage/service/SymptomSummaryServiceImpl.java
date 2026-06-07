package com.triage.medicaltriage.service;

import org.springframework.stereotype.Service;

@Service
public class SymptomSummaryServiceImpl implements SymptomSummaryService{

    public String generateSummary(String conversation) {

        if(conversation == null || conversation.isBlank()) {
            return "";
        }

        if(conversation.length() < 500) {
            return conversation;
        }
        return conversation.substring(0,500);
    }
}
