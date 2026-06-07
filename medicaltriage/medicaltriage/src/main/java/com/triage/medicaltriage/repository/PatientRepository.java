package com.triage.medicaltriage.repository;

import com.triage.medicaltriage.entity.PatientEntity;
import com.triage.medicaltriage.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<PatientEntity, String> {
    PatientEntity findByEmail(String emailId);
}
