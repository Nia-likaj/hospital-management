package com.example.hospitalmanagement;

import com.example.hospitalmanagement.dto.PatientDTO;
import com.example.hospitalmanagement.entity.Department;
import com.example.hospitalmanagement.entity.Patient;
import com.example.hospitalmanagement.repository.DepartmentRepository;
import com.example.hospitalmanagement.repository.PatientRepository;
import com.example.hospitalmanagement.service.PatientService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PatientServiceTest {

    // Mock the PatientRepository to simulate database operations
    @Mock
    private PatientRepository patientRepository;

    // Mock the DepartmentRepository to simulate database operations
    @Mock
    private DepartmentRepository departmentRepository;

    // Inject the mocks into the PatientService instance being tested
    @InjectMocks
    private PatientService patientService;

    // Used to release resources after tests
    private AutoCloseable closeable;

    // This method runs before each test to initialize mocks
    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this); // Initialize mocks
    }

    // This method runs after each test to release resources
    @AfterEach
    void tearDown() throws Exception {
        closeable.close(); // Release resources
    }

    // Test case: Successfully save a patient
    @Test
    void testSavePatient_Success() {
        // Arrange: Set up test data and mock behavior
        PatientDTO patientDTO = new PatientDTO("John", "Doe", LocalDate.of(1990, 5, 15), 1L, null); // Patient DTO
        Department department = new Department("CARD", "Cardiology"); // Department associated with the patient

        // Mock repository behavior
        when(departmentRepository.findById(1L)).thenReturn(Optional.of(department)); // Return department when searched
        when(patientRepository.save(any(Patient.class))).thenReturn(new Patient("John", "Doe", LocalDate.of(1990, 5, 15), department)); // Return patient when saved

        // Act: Call the service method
        Patient result = patientService.savePatient(patientDTO);

        // Assert: Verify the result
        assertNotNull(result, "Patient should not be null"); // Verify result is not null
        assertEquals("John", result.getName(), "Patient name should match"); // Verify patient name

        // Verify interactions: Ensure repositories were called as expected
        verify(departmentRepository, times(1)).findById(1L); // Verify department was searched
        verify(patientRepository, times(1)).save(any(Patient.class)); // Verify patient was saved
    }
}