package com.example.hospitalmanagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * This class is a Data Transfer Object (DTO) used to transfer patient-related information between the client and server.
 * It contains fields for patient name, last name, birth date, department ID, and clinical record, along with validation annotations to ensure data integrity.
 */
public class PatientDTO {

    /**
     * The first name of the patient.
     * This field is mandatory and cannot be blank (empty or whitespace).
     */
    @NotBlank(message = "Patient name is required") // Validation annotation to ensure the field is not blank.
    private String name;

    /**
     * The last name of the patient.
     * This field is mandatory and cannot be blank (empty or whitespace).
     */
    @NotBlank(message = "Last name is required") // Validation annotation to ensure the field is not blank.
    private String lastName;

    /**
     * The birth date of the patient.
     * This field is mandatory and cannot be null.
     */
    @NotNull(message = "Birth date is required") // Validation annotation to ensure the field is not null.
    private LocalDate birthDate;

    /**
     * The ID of the department to which the patient belongs.
     * This field is mandatory and cannot be null.
     */
    @NotNull(message = "Department ID is required") // Validation annotation to ensure the field is not null.
    private Long departmentId;

    /**
     * The clinical record or medical history of the patient.
     * This field is optional and can be null.
     */
    private String clinicalRecord;

    /**
     * Default constructor.
     * Required for frameworks like Spring to create instances of this class during deserialization.
     */
    public PatientDTO() {}

    /**
     * Constructor with all fields.
     * Useful for creating instances of this class with predefined values.
     *
     * @param name The first name of the patient.
     * @param lastName The last name of the patient.
     * @param birthDate The birth date of the patient.
     * @param departmentId The ID of the department to which the patient belongs.
     * @param clinicalRecord The clinical record or medical history of the patient.
     */
    public PatientDTO(String name, String lastName, LocalDate birthDate, Long departmentId, String clinicalRecord) {
        this.name = name;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.departmentId = departmentId;
        this.clinicalRecord = clinicalRecord;
    }

    // Getter and Setter methods for name
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    // Getter and Setter methods for lastName
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    // Getter and Setter methods for birthDate
    public LocalDate getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }

    // Getter and Setter methods for departmentId
    public Long getDepartmentId() { return departmentId; }
    public void setDepartmentId(Long departmentId) { this.departmentId = departmentId; }

    // Getter and Setter methods for clinicalRecord
    public String getClinicalRecord() { return clinicalRecord; }
    public void setClinicalRecord(String clinicalRecord) { this.clinicalRecord = clinicalRecord; }
}