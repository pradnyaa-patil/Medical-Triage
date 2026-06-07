package com.triage.medicaltriage.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "chat_message_entity")
public class ChatMessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sender")
    private String sender;

    @Column(name = "message" , length = 5000)
    private String message;

    @Column(name = "severity")
    private String severity;

    @Column(name = "patient_id")
    private String patientId;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
