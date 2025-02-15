package com.example.hospitalmanagement;

import com.example.hospitalmanagement.dto.AdmissionDTO;
import com.example.hospitalmanagement.entity.AdmissionState;
import com.example.hospitalmanagement.entity.Patient;
import com.example.hospitalmanagement.repository.AdmissionRepository;
import com.example.hospitalmanagement.repository.PatientRepository;
import com.example.hospitalmanagement.service.AdmissionService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AdmissionServiceTest {

    // Mock the AdmissionRepository to simulate database operations
    @Mock
    private AdmissionRepository admissionRepository;

    // Mock the PatientRepository to simulate database operations
    @Mock
    private PatientRepository patientRepository;

    // Inject the mocks into the AdmissionService instance being tested
    @InjectMocks
    private AdmissionService admissionService;

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

    // Test case: Successfully admit a patient
    @Test
    void testAdmitPatient_Success() {
        // Arrange: Set up test data and mock behavior
        Long patientId = 7L; // Valid patient ID
        AdmissionDTO admissionDTO = new AdmissionDTO(patientId, "Heart Surgery"); // Admission DTO

        Patient patient = new Patient();
        patient.setId(patientId); // Set patient ID

        AdmissionState admission = new AdmissionState(patient, "Heart Surgery", LocalDateTime.now()); // Expected admission

        // Mock repository behavior
        when(patientRepository.findById(patientId)).thenReturn(Optional.of(patient)); // Return patient when searched
        when(admissionRepository.save(any(AdmissionState.class))).thenReturn(admission); // Return admission when saved

        // Act: Call the service method
        AdmissionState result = admissionService.admitPatient(admissionDTO);

        // Assert: Verify the result
        assertNotNull(result, "Admission should not be null"); // Verify result is not null
        assertEquals("Heart Surgery", result.getCause(), "Cause should match"); // Verify cause
        assertEquals(patient, result.getPatient(), "Patient should match"); // Verify patient

        // Verify interactions: Ensure repositories were called as expected
        verify(patientRepository, times(1)).findById(patientId); // Verify patient was searched
        verify(admissionRepository, times(1)).save(any(AdmissionState.class)); // Verify admission was saved
    }

    // Test case: Fail to admit a patient because the patient is not found
    @Test
    void testAdmitPatient_PatientNotFound() {
        // Arrange: Set up test data and mock behavior
        Long patientId = 8L; // Non-existent patient ID
        AdmissionDTO admissionDTO = new AdmissionDTO(patientId, "General Checkup"); // Admission DTO

        // Mock repository behavior (patient not found)
        when(patientRepository.findById(patientId)).thenReturn(Optional.empty());

        // Act & Assert: Call the service method and expect an exception
        Exception exception = assertThrows(RuntimeException.class, () -> admissionService.admitPatient(admissionDTO));
        assertEquals("Patient not found", exception.getMessage(), "Exception message should match"); // Verify exception message

        // Verify interactions: Ensure repositories were called as expected
        verify(patientRepository, times(1)).findById(patientId); // Verify patient was searched
        verify(admissionRepository, never()).save(any(AdmissionState.class)); // Verify admission was not saved
    }

    // Test case: Successfully retrieve admissions for a patient
    @Test
    void testGetAdmissionsByPatient_Success() {
        // Arrange: Set up test data and mock behavior
        Long patientId = 7L; // Valid patient ID
        Patient patient = new Patient();
        patient.setId(patientId); // Set patient ID

        AdmissionState admission = new AdmissionState(patient, "Heart Surgery", LocalDateTime.now()); // Admission record
        List<AdmissionState> admissions = Collections.singletonList(admission); // List of admissions

        // Mock repository behavior
        when(admissionRepository.findByPatientId(patientId)).thenReturn(admissions); // Return admissions for patient

        // Act: Call the service method
        List<AdmissionDTO> result = admissionService.getAdmissionsByPatient(patientId);

        // Assert: Verify the result
        assertNotNull(result, "Result should not be null"); // Verify result is not null
        assertEquals(1, result.size(), "There should be one admission"); // Verify number of admissions
        assertEquals("Heart Surgery", result.get(0).getCause(), "Cause should match"); // Verify cause
        assertEquals(patientId, result.get(0).getPatientId(), "Patient ID should match"); // Verify patient ID

        // Verify interactions: Ensure repository was called as expected
        verify(admissionRepository, times(1)).findByPatientId(patientId); // Verify admissions were searched
    }

    // Test case: No admissions found for a patient
    @Test
    void testGetAdmissionsByPatient_NoAdmissionsFound() {
        // Arrange: Set up test data and mock behavior
        Long patientId = 9L; // Valid patient ID with no admissions

        // Mock repository behavior (no admissions found)
        when(admissionRepository.findByPatientId(patientId)).thenReturn(Collections.emptyList());

        // Act: Call the service method
        List<AdmissionDTO> result = admissionService.getAdmissionsByPatient(patientId);

        // Assert: Verify the result
        assertNotNull(result, "Result should not be null"); // Verify result is not null
        assertTrue(result.isEmpty(), "Result should be empty"); // Verify result is empty

        // Verify interactions: Ensure repository was called as expected
        verify(admissionRepository, times(1)).findByPatientId(patientId); // Verify admissions were searched
    }
}