package com.example.hospitalmanagement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class AdmissionState {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime enteringDate = LocalDateTime.now();
    private LocalDateTime exitingDate;
    private String cause;
    private String reason;
    private boolean discharge = false;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    // Default Constructor
    public AdmissionState() {}

    // Constructor with Required Fields
    public AdmissionState(Patient patient, String cause, LocalDateTime enteringDate) {
        this.patient = patient;
        this.cause = cause;
        this.enteringDate = enteringDate;
    }
}