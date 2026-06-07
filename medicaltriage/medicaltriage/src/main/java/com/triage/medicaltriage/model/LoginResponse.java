package com.triage.medicaltriage.model;

import lombok.Data;

@Data
public class LoginResponse {

    private String token;
    private String firstName;
    private String patientId;
}
