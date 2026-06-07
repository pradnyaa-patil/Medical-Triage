package com.triage.medicaltriage.repository;

import com.triage.medicaltriage.entity.SeverityAssessment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeverityAssessmentRepository extends JpaRepository<SeverityAssessment,String> {
    List<SeverityAssessment> findByPatientId(String patientId);

    List<SeverityAssessment> findBySeverity(String emergency);

    List<SeverityAssessment> findByPatientIdOrderByCreatedAtDesc(String patientId);
}
