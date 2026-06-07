package com.triage.medicaltriage.repository;

import com.triage.medicaltriage.entity.ChatMessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessageEntity, Long> {

    List<ChatMessageEntity> findByPatientId(String patientId);

    List<ChatMessageEntity> findByPatientIdOrderByCreatedAtAsc(String patientId);
}
