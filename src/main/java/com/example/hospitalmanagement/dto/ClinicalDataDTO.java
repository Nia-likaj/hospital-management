package com.example.hospitalmanagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ClinicalDataDTO {

    @NotNull(message = "Patient ID is required")
    private Long patientId;

    @NotBlank(message = "Clinical record cannot be empty")
    private String clinicalRecord;

    // Constructors
    public ClinicalDataDTO() {}

    public ClinicalDataDTO(Long patientId, String clinicalRecord) {
        this.patientId = patientId;
        this.clinicalRecord = clinicalRecord;
    }

    // Getters and Setters
    public Long getPatientId() { return patientId; }
    public void setPatientId(Long patientId) { this.patientId = patientId; }

    public String getClinicalRecord() { return clinicalRecord; }
    public void setClinicalRecord(String clinicalRecord) { this.clinicalRecord = clinicalRecord; }
}
