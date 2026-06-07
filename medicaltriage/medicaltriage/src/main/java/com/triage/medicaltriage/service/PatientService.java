package com.triage.medicaltriage.service;

import com.triage.medicaltriage.model.LoginRequest;
import com.triage.medicaltriage.model.Patient;
import com.triage.medicaltriage.model.RegistrationResponse;

import java.util.List;

public interface PatientService {

    RegistrationResponse save(Patient patient);
    List<Patient> getAllPatients();
    Patient findById(String id);


    String login(LoginRequest loginRequest);
}
