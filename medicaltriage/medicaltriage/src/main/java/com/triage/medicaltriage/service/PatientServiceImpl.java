package com.triage.medicaltriage.service;

import com.triage.medicaltriage.entity.PatientEntity;
import com.triage.medicaltriage.model.LoginRequest;
import com.triage.medicaltriage.model.Patient;
import com.triage.medicaltriage.model.RegistrationResponse;
import com.triage.medicaltriage.repository.PatientRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    List<Patient> patients = new ArrayList<>();

    @Override
    public RegistrationResponse save(Patient patient) {
        PatientEntity existingPatient = patientRepository.findByEmail(patient.getEmail());

        if (existingPatient != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Patient already registered");
        }

        if (patient.getPatientId() == null) {
            patient.setPatientId(UUID.randomUUID().toString());
        }
        patient.setPassword(passwordEncoder.encode(patient.getPassword()));

        patient.setCreatedAt(LocalDateTime.now());

        PatientEntity patientEntity = new PatientEntity();
        BeanUtils.copyProperties(patient, patientEntity);
        patientRepository.save(patientEntity);
        return new RegistrationResponse("Registration Successful");
    }

    @Override
    public List<Patient> getAllPatients() {
        List<PatientEntity> patientEntityList = patientRepository.findAll();
        List<Patient> patientList = patientEntityList
                .stream()
                .map(patientEntity -> {
                    Patient patient = new Patient();
                    BeanUtils.copyProperties(patientEntity, patient);
                    return patient;
                })
                .collect(Collectors.toList());
        return patientList;
    }

    @Override
    public Patient findById(String id) {
      PatientEntity patientEntity = patientRepository.findById(id).get();
      Patient patient = new Patient();
      BeanUtils.copyProperties(patientEntity, patient);
      return patient;
    }

    @Override
    public String login(LoginRequest loginRequest) {
        PatientEntity patientEntity =
                patientRepository.findByEmail(loginRequest.getEmail());
        if (patientEntity.getEmail().equalsIgnoreCase(loginRequest.getEmail())
                && patientEntity.getPassword().equalsIgnoreCase(loginRequest.getPassword())) {
            return "success";
        }

        return "failed";
    }
}
