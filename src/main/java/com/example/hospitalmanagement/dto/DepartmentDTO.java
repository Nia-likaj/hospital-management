package com.example.hospitalmanagement.dto;

/**
 * This class is a Data Transfer Object (DTO) used to transfer department-related information between the client and server.
 * It contains fields for department ID, code, and name, along with constructors and getter/setter methods for accessing and modifying the fields.
 */
public class DepartmentDTO {

    /**
     * The unique identifier for the department.
     */
    private Long id;

    /**
     * The code representing the department (e.g., "HR" for Human Resources).
     */
    private String code;

    /**
     * The name of the department (e.g., "Cardiology").
     */
    private String name;

    /**
     * Default constructor.
     * Required for frameworks like Spring to create instances of this class during deserialization.
     */
    public DepartmentDTO() {}

    /**
     * Constructor with all fields.
     * Useful for creating instances of this class with predefined values.
     *
     * @param id The unique identifier for the department.
     * @param code The code representing the department.
     * @param name The name of the department.
     */
    public DepartmentDTO(Long id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }

    // Getter and Setter methods for id
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    // Getter and Setter methods for code
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    // Getter and Setter methods for name
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}