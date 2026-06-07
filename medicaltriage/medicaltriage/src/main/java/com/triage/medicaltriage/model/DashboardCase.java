package com.triage.medicaltriage.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DashboardCase {
    private String patientId;
    private String severity;
    private String symptoms;
    private String reason;
    private String patientName;
    private int age;
    private String gender;
}
