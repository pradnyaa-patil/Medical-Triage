package com.triage.medicaltriage.service;

import com.triage.medicaltriage.model.ChatMessage;
import com.triage.medicaltriage.model.Patient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromptBuilderServiceImpl implements PromptBuilderService {
    public String buildPrompt(Patient patient, List<ChatMessage> history, String currentMessage) {

        StringBuilder prompt = new StringBuilder();

        prompt.append("""
You are a medical intake chatbot.

Your role is to collect symptom information and provide a brief recommendation when sufficient information has been gathered.

PATIENT PROFILE:
""");

        prompt.append("Age: ")
                .append(patient.getAge())
                .append("\n");

        prompt.append("Gender: ")
                .append(patient.getGender())
                .append("\n");

        if (patient.getExistingCondition() != null &&
                !patient.getExistingCondition().isBlank()) {

            prompt.append("Existing Conditions: ")
                    .append(patient.getExistingCondition())
                    .append("\n");
        }

        if (patient.getAllergies() != null &&
                !patient.getAllergies().isBlank()) {

            prompt.append("Allergies: ")
                    .append(patient.getAllergies())
                    .append("\n");
        }

        if (patient.getMedication() != null &&
                !patient.getMedication().isBlank()) {

            prompt.append("Current Medication: ")
                    .append(patient.getMedication())
                    .append("\n");
        }

        prompt.append("""

CONVERSATION HISTORY:
""");

        // Send only patient messages to avoid repetition
        for (ChatMessage msg : history) {

            if ("PATIENT".equalsIgnoreCase(msg.getSender())) {

                prompt.append("Patient: ")
                        .append(msg.getMessage())
                        .append("\n");
            }
        }

        prompt.append("\nLATEST PATIENT MESSAGE:\n");
        prompt.append(currentMessage);

        prompt.append("""

INSTRUCTIONS:

The conversation history is provided ONLY for context.

DO NOT:
- Summarize the entire conversation.
- Repeat symptoms already mentioned earlier.
- Repeat patient history.
- List all collected symptoms.
- Mention diagnoses.
- Mention medical conditions not explicitly stated.
- Invent symptoms.
- Invent durations.
- Invent risk factors.
- Mention severity levels.
- Use the words:
  LOW
  MEDIUM
  SEVERE
  URGENT
  EMERGENCY

ASK QUESTIONS ONLY IF NECESSARY.

You may ask ONE follow-up question only when critical information is still missing.

You should STOP asking questions and provide a recommendation when the conversation already contains:

- Main symptom
- Duration or onset (if available)
- Important warning-sign information

If enough information is already available:

Provide:


Recommendation: <brief recommendation>

The summary must contain ONLY symptoms explicitly mentioned by the patient.

OUTPUT RULES:

- Plain text only.
- Maximum 3 sentences.
- No markdown.
- No bullet points.
- No numbering.
- No JSON.
- No severity labels.
- No diagnosis.

GOOD EXAMPLE:


Recommendation: Please arrange a medical evaluation soon. Seek medical attention immediately if symptoms worsen or new concerning symptoms develop.

QUESTION EXAMPLE:

How long have you been experiencing the swelling?

SELF CHECK BEFORE RESPONDING:

1. Am I repeating old symptoms unnecessarily?
2. Am I summarizing the entire conversation?
3. Am I mentioning LOW, MEDIUM, SEVERE, URGENT, or EMERGENCY?
4. Am I inventing symptoms?
5. Do I already have enough information to provide a recommendation?

If enough information exists, provide a recommendation instead of another question.

Return only the final response text.
""");

        return prompt.toString();
    }
}
