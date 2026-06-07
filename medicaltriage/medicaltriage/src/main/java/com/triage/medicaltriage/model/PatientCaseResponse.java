package com.triage.medicaltriage.model;

import com.triage.medicaltriage.entity.SeverityAssessment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientCaseResponse {

    private Patient patient;

    private List<ChatMessage> chatHistory;

    private List<SeverityAssessment> severityHistory;
}
