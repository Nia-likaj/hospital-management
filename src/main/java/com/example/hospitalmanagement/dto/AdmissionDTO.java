package com.example.hospitalmanagement.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * This class is a Data Transfer Object (DTO) used to transfer admission-related data between the client and server.
 * It contains fields for patient ID and admission cause, along with validation annotations to ensure data integrity.
 */
@Setter // Lombok annotation to automatically generate setter methods for all fields.
@Getter // Lombok annotation to automatically generate getter methods for all fields.
public class AdmissionDTO {

    /**
     * The ID of the patient being admitted.
     * This field is mandatory and cannot be null.
     */
    @NotNull(message = "Patient ID is required") // Validation annotation to ensure the field is not null.
    private Long patientId;

    /**
     * The cause or reason for the patient's admission.
     * This field is mandatory and cannot be null.
     */
    @NotNull(message = "Admission cause is required") // Validation annotation to ensure the field is not null.
    private String cause;

    /**
     * Default constructor.
     * Required for frameworks like Spring to create instances of this class.
     */
    public AdmissionDTO() {}

    /**
     * Constructor with all fields.
     * Useful for creating instances of this class with predefined values.
     *
     * @param patientId The ID of the patient being admitted.
     * @param cause The cause or reason for the patient's admission.
     */
    public AdmissionDTO(Long patientId, String cause) {
        this.patientId = patientId;
        this.cause = cause;
    }
}