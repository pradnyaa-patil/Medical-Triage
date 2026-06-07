package com.triage.medicaltriage.service;

import com.triage.medicaltriage.entity.ChatMessageEntity;
import com.triage.medicaltriage.entity.PatientCurrentStatusEntity;
import com.triage.medicaltriage.entity.PatientEntity;
import com.triage.medicaltriage.entity.SeverityAssessment;
import com.triage.medicaltriage.model.*;
import com.triage.medicaltriage.repository.ChatMessageRepository;

import com.triage.medicaltriage.repository.PatientCurrentStatusRepository;
import com.triage.medicaltriage.repository.PatientRepository;
import com.triage.medicaltriage.repository.SeverityAssessmentRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @Autowired
    private SeverityRuleService ruleService;

    @Autowired
    private SeverityAssessmentRepository severityRepository;

    @Autowired
    private PromptBuilderService promptBuilderService;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private OpenAIService openAIService;

    @Autowired
    private PatientCurrentStatusRepository patientCurrentStatusRepository;

    @Autowired
    private SymptomSummaryService symptomSummaryService;

    public ChatResponse processMessage(ChatRequest request) {

        ChatResponse response = new ChatResponse();

        PatientEntity patientEntity = patientRepository.findById(request.getPatientId()).get();

        Patient patient = new Patient();
        BeanUtils.copyProperties(patientEntity, patient);

        /** collecting all the conversation of the patient*/

        List<ChatMessageEntity> historyEntity = chatMessageRepository.findByPatientIdOrderByCreatedAtAsc(request.getPatientId());
        String conversation = historyEntity.stream()
                .filter(msg -> "PATIENT".equalsIgnoreCase(msg.getSender()))
                .map(ChatMessageEntity::getMessage)
                .collect(Collectors.joining(" "));

        conversation += " " + request.getMessage();

        /** collecting all the conversation of the patient completed*/

        /**
         * As Severity should be decided on the basis of whole conversation and not on the last message sent by the patient.
         */

        SeverityResult result = ruleService.determineSeverity(conversation, patientEntity.getAge(), patientEntity.getExistingCondition());

        SeverityAssessment assessment = new SeverityAssessment();

        assessment.setPatientId(request.getPatientId());
        assessment.setSymptoms(request.getMessage());
        assessment.setSeverity(result.getSeverity());
        assessment.setReason(result.getReason());
        assessment.setCreatedAt(LocalDateTime.now());

        severityRepository.save(assessment);

        ChatMessageEntity chatMessageEntity = new ChatMessageEntity();
        chatMessageEntity.setPatientId(request.getPatientId());
        chatMessageEntity.setSender("PATIENT");
        chatMessageEntity.setMessage(request.getMessage());
        chatMessageEntity.setCreatedAt(LocalDateTime.now());
        chatMessageRepository.save(chatMessageEntity);

        String patientContext =
                """
                Patient Information:
        
                Age: %d
                Gender: %s
                Existing Conditions: %s
                Allergies: %s
                Current Medications: %s
                """
                        .formatted(
                                patient.getAge(),
                                patient.getGender(),
                                patient.getExistingCondition(),
                                patient.getAllergies(),
                                patient.getMedication()
                        );

        List<ChatMessage> chatMessageList = historyEntity.stream()
                .map(chatMessage -> {
                    ChatMessage entity = new ChatMessage();
                    BeanUtils.copyProperties(chatMessage,entity);
                    return entity;
                })
                .toList();


        /**
         * considering patients medical history , age etc information provided during the registration
         */
        String prompt = promptBuilderService.buildPrompt(patient, chatMessageList, request.getMessage());

        String aiReply = openAIService.getChatResponse(prompt);

        ChatMessageEntity botMessageEntity = new ChatMessageEntity();
        botMessageEntity.setPatientId(request.getPatientId());
        botMessageEntity.setSender("BOT");
        botMessageEntity.setMessage(aiReply);
        botMessageEntity.setCreatedAt(LocalDateTime.now());
        chatMessageRepository.save(botMessageEntity);

        response.setReply(aiReply);

        response.setSeverity(result.getSeverity());

        /** ************ saving current status ************/
        PatientCurrentStatusEntity currentStatusEntity = patientCurrentStatusRepository.findById(request.getPatientId()).orElse(new PatientCurrentStatusEntity());

        currentStatusEntity.setPatientId(request.getPatientId());
        currentStatusEntity.setSymptomSummary(conversation);
        currentStatusEntity.setSeverity(result.getSeverity());
        currentStatusEntity.setReason(result.getReason());
        currentStatusEntity.setUpdatedAt(LocalDateTime.now());
        patientCurrentStatusRepository.save(currentStatusEntity);

        /** ************ saving current status completed ************/


        return response;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ChatMessage> getHistory(String patientId) {
        List<ChatMessageEntity> chatMessageEntityList = chatMessageRepository.findByPatientIdOrderByCreatedAtAsc(patientId);
        String a = chatMessageRepository.findById(1L).get().getSender();

        List<ChatMessage> chatMessageList = chatMessageEntityList.stream()
                .map(chatMessage -> {
                    ChatMessage entity = new ChatMessage();
                    BeanUtils.copyProperties(chatMessage,entity);
                    return entity;
                })
                .toList(); // or .collect(Collectors.toList()); if using Java 15 or older
        return chatMessageList;
    }
}

