package com.triage.medicaltriage.service;

import com.triage.medicaltriage.model.PatientCaseResponse;

public interface PatientCaseService {
    PatientCaseResponse getCase(String patientId);
}
