package com.example.hospitalmanagement.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Setter
@Getter
public class AdmissionDTO {

    @NotNull(message = "Patient ID is required")
    private Long patientId;

    @NotNull(message = "Admission cause is required")
    private String cause;

    private LocalDateTime admissionDate;

    // Default Constructor
    public AdmissionDTO() {}

    // Constructor with All Fields
    public AdmissionDTO(Long patientId, String cause, LocalDateTime admissionDate) {
        this.patientId = patientId;
        this.cause = cause;
        this.admissionDate = admissionDate;
    }

    public AdmissionDTO(long l, String heartSurgery) {
    }
}
