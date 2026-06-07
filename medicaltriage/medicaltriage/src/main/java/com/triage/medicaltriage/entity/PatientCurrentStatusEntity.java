package com.triage.medicaltriage.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "patient_current_status")
public class PatientCurrentStatusEntity {

    @Id
    private String patientId;

    private String severity;

    @Column(length = 5000)
    private String symptomSummary;

    @Column(length = 3000)
    private String reason;

    private LocalDateTime updatedAt;

    public PatientCurrentStatusEntity() {
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getSymptomSummary() {
        return symptomSummary;
    }

    public void setSymptomSummary(String symptomSummary) {
        this.symptomSummary = symptomSummary;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}