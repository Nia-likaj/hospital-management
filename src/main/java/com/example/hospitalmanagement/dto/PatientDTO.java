package com.example.hospitalmanagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class PatientDTO {

    @NotBlank(message = "Patient name is required")
    private String name;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotNull(message = "Birth date is required")
    private LocalDate birthDate;

    @NotNull(message = "Department ID is required")
    private Long departmentId;

    private String clinicalRecord;

    // Constructors
    public PatientDTO() {}

    public PatientDTO(String name, String lastName, LocalDate birthDate, Long departmentId, String clinicalRecord) {
        this.name = name;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.departmentId = departmentId;
        this.clinicalRecord = clinicalRecord;
    }

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public LocalDate getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }

    public Long getDepartmentId() { return departmentId; }
    public void setDepartmentId(Long departmentId) { this.departmentId = departmentId; }

    public String getClinicalRecord() { return clinicalRecord; }
    public void setClinicalRecord(String clinicalRecord) { this.clinicalRecord = clinicalRecord; }
}
