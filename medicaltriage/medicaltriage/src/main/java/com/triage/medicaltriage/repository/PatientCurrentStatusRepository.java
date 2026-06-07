package com.triage.medicaltriage.repository;

import com.triage.medicaltriage.entity.PatientCurrentStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientCurrentStatusRepository extends JpaRepository<PatientCurrentStatusEntity,String> {

    List<PatientCurrentStatusEntity> findBySeverity(String severity);
}
