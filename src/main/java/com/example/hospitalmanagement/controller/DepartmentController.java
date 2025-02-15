package com.example.hospitalmanagement.controller;

import com.example.hospitalmanagement.dto.DepartmentDTO;
import com.example.hospitalmanagement.entity.Department;
import com.example.hospitalmanagement.service.DepartmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This class is a Spring REST controller responsible for handling HTTP requests related to hospital departments.
 * It exposes endpoints for saving a department and retrieving all departments.
 */
@RestController // Indicates that this class is a REST controller, meaning it handles HTTP requests and returns JSON/XML responses.
@RequestMapping("/departments") // Maps all endpoints in this controller to the base URL path "/departments".
public class DepartmentController {

    // DepartmentService is a dependency that handles the business logic for departments.
    private final DepartmentService departmentService;

    // Constructor-based dependency injection is used to inject the DepartmentService bean.
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    /**
     * Endpoint to save a department.
     * This method handles HTTP POST requests to the "/departments" URL.
     *
     * @param departmentDTO A data transfer object (DTO) containing the details of the department to be saved.
     * @return ResponseEntity<Department> Returns the saved department entity with an HTTP 200 OK status.
     */
    @PostMapping // Maps HTTP POST requests to this method.
    public ResponseEntity<Department> saveDepartment(@RequestBody DepartmentDTO departmentDTO) {
        // Calls the saveDepartment method in the DepartmentService to process and save the department.
        // Returns the saved department entity wrapped in a ResponseEntity with an HTTP 200 OK status.
        return ResponseEntity.ok(departmentService.saveDepartment(departmentDTO));
    }

    /**
     * Endpoint to retrieve all departments.
     * This method handles HTTP GET requests to the "/departments" URL.
     *
     * @return ResponseEntity<List<DepartmentDTO>> Returns a list of all departments with an HTTP 200 OK status.
     */
    @GetMapping // Maps HTTP GET requests to this method.
    public ResponseEntity<List<DepartmentDTO>> getAllDepartments() {
        // Calls the getAllDepartments method in the DepartmentService to fetch all departments.
        // Returns the list of departments wrapped in a ResponseEntity with an HTTP 200 OK status.
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }
}