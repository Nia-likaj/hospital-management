package com.example.hospitalmanagement.service;

import com.example.hospitalmanagement.dto.DepartmentDTO;
import com.example.hospitalmanagement.entity.Department;
import com.example.hospitalmanagement.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This class is a service layer component that handles business logic related to hospital departments.
 * It interacts with the `DepartmentRepository` to perform operations on department records.
 */
@Service // Marks this class as a Spring service component, making it a managed bean in the Spring context.
public class DepartmentService {

    private final DepartmentRepository departmentRepository; // Repository for department-related operations.

    /**
     * Constructor for dependency injection.
     *
     * @param departmentRepository The repository for department-related operations.
     */
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    /**
     * Saves a new department to the database.
     *
     * @param departmentDTO A data transfer object containing the details of the department.
     * @return The saved `Department` entity.
     */
    public Department saveDepartment(DepartmentDTO departmentDTO) {
        // Create a new department entity using the data from the DTO.
        Department department = new Department(departmentDTO.getCode(), departmentDTO.getName());

        // Save the department to the database and return it.
        return departmentRepository.save(department);
    }

    /**
     * Retrieves all departments from the database.
     *
     * @return A list of `DepartmentDTO` objects representing all departments.
     */
    public List<DepartmentDTO> getAllDepartments() {
        // Fetch all departments from the database.
        return departmentRepository.findAll().stream()
                // Convert each `Department` entity to a `DepartmentDTO` object.
                .map(department -> new DepartmentDTO(department.getId(), department.getCode(), department.getName()))
                // Collect the results into a list.
                .collect(Collectors.toList());
    }
}