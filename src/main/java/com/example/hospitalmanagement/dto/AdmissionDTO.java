package com.example.hospitalmanagement.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AdmissionDTO {

    @NotNull(message = "Patient ID is required")
    private Long patientId;

    @NotNull(message = "Admission cause is required")
    private String cause;

    // Default Constructor
    public AdmissionDTO() {}

    // Constructor with All Fields
    public AdmissionDTO(Long patientId, String cause) {
        this.patientId = patientId;
        this.cause = cause;
    }
}