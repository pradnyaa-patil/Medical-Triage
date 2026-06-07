package com.triage.medicaltriage.controller;

import com.triage.medicaltriage.model.*;
import com.triage.medicaltriage.service.AuthService;
import com.triage.medicaltriage.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class MedicalTriageController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private AuthService authService;


    @PostMapping("/register")
    public RegistrationResponse save(@RequestBody Patient patient) {
       return patientService.save(patient);

    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @GetMapping("/{id}")
    public Patient findById(@PathVariable String id) {
        return patientService.findById(id);
    }

    @GetMapping
    public List<Patient> getPatients() {
       return patientService.getAllPatients();

    }


}
