package com.example.hospitalmanagement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ClinicalData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String clinicalRecord;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    public ClinicalData() {}

    public ClinicalData(Patient patient, String clinicalRecord) {
        this.patient = patient;
        this.clinicalRecord = clinicalRecord;
    }
}
