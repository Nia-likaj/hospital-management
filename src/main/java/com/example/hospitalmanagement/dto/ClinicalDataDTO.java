package com.example.hospitalmanagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * This class is a Data Transfer Object (DTO) used to transfer clinical data-related information between the client and server.
 * It contains fields for patient ID and clinical record, along with validation annotations to ensure data integrity.
 */
public class ClinicalDataDTO {

    /**
     * The ID of the patient associated with the clinical record.
     * This field is mandatory and cannot be null.
     */
    @NotNull(message = "Patient ID is required") // Validation annotation to ensure the field is not null.
    private Long patientId;

    /**
     * The clinical record or details of the patient's medical condition.
     * This field is mandatory and cannot be blank (empty or whitespace).
     */
    @NotBlank(message = "Clinical record cannot be empty") // Validation annotation to ensure the field is not blank.
    private String clinicalRecord;

    /**
     * Default constructor.
     * Required for frameworks like Spring to create instances of this class during deserialization.
     */
    public ClinicalDataDTO() {}

    /**
     * Constructor with all fields.
     * Useful for creating instances of this class with predefined values.
     *
     * @param patientId The ID of the patient associated with the clinical record.
     * @param clinicalRecord The clinical record or details of the patient's medical condition.
     */
    public ClinicalDataDTO(Long patientId, String clinicalRecord) {
        this.patientId = patientId;
        this.clinicalRecord = clinicalRecord;
    }

    // Getter and Setter methods for patientId
    public Long getPatientId() { return patientId; }
    public void setPatientId(Long patientId) { this.patientId = patientId; }

    // Getter and Setter methods for clinicalRecord
    public String getClinicalRecord() { return clinicalRecord; }
    public void setClinicalRecord(String clinicalRecord) { this.clinicalRecord = clinicalRecord; }
}