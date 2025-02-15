package com.example.hospitalmanagement.controller;

import com.example.hospitalmanagement.dto.PatientDTO;
import com.example.hospitalmanagement.entity.Patient;
import com.example.hospitalmanagement.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This class is a Spring REST controller responsible for handling HTTP requests related to patients.
 * It exposes endpoints for saving a patient and retrieving all patients.
 */
@RestController // Indicates that this class is a REST controller, meaning it handles HTTP requests and returns JSON/XML responses.
@RequestMapping("/patients") // Maps all endpoints in this controller to the base URL path "/patients".
public class PatientController {

    // PatientService is a dependency that handles the business logic for patients.
    private final PatientService patientService;

    // Constructor-based dependency injection is used to inject the PatientService bean.
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    /**
     * Endpoint to save a patient.
     * This method handles HTTP POST requests to the "/patients" URL.
     *
     * @param patientDTO A data transfer object (DTO) containing the details of the patient to be saved.
     * @return ResponseEntity<Patient> Returns the saved patient entity with an HTTP 200 OK status.
     */
    @PostMapping // Maps HTTP POST requests to this method.
    public ResponseEntity<Patient> savePatient(@RequestBody PatientDTO patientDTO) {
        // Calls the savePatient method in the PatientService to process and save the patient.
        // Returns the saved patient entity wrapped in a ResponseEntity with an HTTP 200 OK status.
        return ResponseEntity.ok(patientService.savePatient(patientDTO));
    }

    /**
     * Endpoint to retrieve all patients.
     * This method handles HTTP GET requests to the "/patients" URL.
     *
     * @return ResponseEntity<List<PatientDTO>> Returns a list of all patients with an HTTP 200 OK status.
     */
    @GetMapping // Maps HTTP GET requests to this method.
    public ResponseEntity<List<PatientDTO>> getAllPatients() {
        // Calls the getAllPatients method in the PatientService to fetch all patients.
        // Returns the list of patients wrapped in a ResponseEntity with an HTTP 200 OK status.
        return ResponseEntity.ok(patientService.getAllPatients());
    }
}