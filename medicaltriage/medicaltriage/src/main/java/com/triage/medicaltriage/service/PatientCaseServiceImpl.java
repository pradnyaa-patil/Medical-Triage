package com.triage.medicaltriage.service;

import com.triage.medicaltriage.entity.ChatMessageEntity;
import com.triage.medicaltriage.entity.PatientEntity;
import com.triage.medicaltriage.entity.SeverityAssessment;
import com.triage.medicaltriage.model.ChatMessage;
import com.triage.medicaltriage.model.Patient;
import com.triage.medicaltriage.model.PatientCaseResponse;
import com.triage.medicaltriage.repository.ChatMessageRepository;
import com.triage.medicaltriage.repository.PatientRepository;
import com.triage.medicaltriage.repository.SeverityAssessmentRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientCaseServiceImpl implements PatientCaseService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private ChatMessageRepository chatRepository;

    @Autowired
    private SeverityAssessmentRepository
            severityRepository;

    public PatientCaseResponse getCase(String patientId) {

        PatientEntity patientEntity = patientRepository.findById(patientId).orElseThrow();

        List<ChatMessageEntity> chatMessageEntityList = chatRepository.findByPatientIdOrderByCreatedAtAsc(patientId);

        List<SeverityAssessment> severityHistory = severityRepository.findByPatientIdOrderByCreatedAtDesc(patientId);

        PatientCaseResponse response = new PatientCaseResponse();

        Patient patient = new Patient();
        BeanUtils.copyProperties(patient, patientEntity);
        response.setPatient(patient);


        List<ChatMessage> chatMessageList = chatMessageEntityList
                .stream()
                .map(chatMessageEntity -> {
                    ChatMessage chatMessage = new ChatMessage();
                    BeanUtils.copyProperties(chatMessageEntity, chatMessage);
                    return chatMessage;
                })
                .collect(Collectors.toList());

        response.setChatHistory(chatMessageList);
        response.setSeverityHistory(
                severityHistory);

        return response;
    }
}
