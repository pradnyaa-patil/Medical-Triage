package com.triage.medicaltriage.controller;


import com.triage.medicaltriage.model.PatientCaseResponse;
import com.triage.medicaltriage.service.PatientCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/patient-case")
public class PatientCaseController {

    @Autowired
    private PatientCaseService service;

    @GetMapping("/{patientId}")
    public PatientCaseResponse getCase(
            @PathVariable String patientId) {

        return service.getCase(patientId);
    }
}
