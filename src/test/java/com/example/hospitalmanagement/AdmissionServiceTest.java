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

    @Mock
    private AdmissionRepository admissionRepository;

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private AdmissionService admissionService;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);  // Initialize mocks
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();  // Release resources
    }

    @Test
    void testAdmitPatient_Success() {
        // Arrange
        Long patientId = 7L;  // Use a valid patient ID from your database
        AdmissionDTO admissionDTO = new AdmissionDTO(patientId, "Heart Surgery");

        Patient patient = new Patient();
        patient.setId(patientId);  // Ensure the patient ID matches the one in the DTO

        AdmissionState admission = new AdmissionState(patient, "Heart Surgery", LocalDateTime.now());

        // Mock the repository to return the patient
        when(patientRepository.findById(patientId)).thenReturn(Optional.of(patient));
        when(admissionRepository.save(any(AdmissionState.class))).thenReturn(admission);

        // Act
        AdmissionState result = admissionService.admitPatient(admissionDTO);

        // Assert
        assertNotNull(result, "Admission should not be null");
        assertEquals("Heart Surgery", result.getCause(), "Cause should match");
        assertEquals(patient, result.getPatient(), "Patient should match");

        // Verify interactions
        verify(patientRepository, times(1)).findById(patientId);
        verify(admissionRepository, times(1)).save(any(AdmissionState.class));
    }

    @Test
    void testAdmitPatient_PatientNotFound() {
        // Arrange
        Long patientId = 8L;  // Use a valid patient ID that does not exist
        AdmissionDTO admissionDTO = new AdmissionDTO(patientId, "General Checkup");

        // Mock the repository to return an empty Optional (patient not found)
        when(patientRepository.findById(patientId)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> admissionService.admitPatient(admissionDTO));
        assertEquals("Patient not found", exception.getMessage(), "Exception message should match");

        // Verify interactions
        verify(patientRepository, times(1)).findById(patientId);
        verify(admissionRepository, never()).save(any(AdmissionState.class));
    }

    @Test
    void testGetAdmissionsByPatient_Success() {
        // Arrange
        Long patientId = 7L;  // Use a valid patient ID
        Patient patient = new Patient();
        patient.setId(patientId);

        AdmissionState admission = new AdmissionState(patient, "Heart Surgery", LocalDateTime.now());
        List<AdmissionState> admissions = Collections.singletonList(admission);

        // Mock the repository to return admissions for the patient
        when(admissionRepository.findByPatientId(patientId)).thenReturn(admissions);

        // Act
        List<AdmissionDTO> result = admissionService.getAdmissionsByPatient(patientId);

        // Assert
        assertNotNull(result, "Result should not be null");
        assertEquals(1, result.size(), "There should be one admission");
        assertEquals("Heart Surgery", result.get(0).getCause(), "Cause should match");
        assertEquals(patientId, result.get(0).getPatientId(), "Patient ID should match");

        // Verify interactions
        verify(admissionRepository, times(1)).findByPatientId(patientId);
    }

    @Test
    void testGetAdmissionsByPatient_NoAdmissionsFound() {
        // Arrange
        Long patientId = 9L;  // Use a valid patient ID with no admissions

        // Mock the repository to return an empty list (no admissions found)
        when(admissionRepository.findByPatientId(patientId)).thenReturn(Collections.emptyList());

        // Act
        List<AdmissionDTO> result = admissionService.getAdmissionsByPatient(patientId);

        // Assert
        assertNotNull(result, "Result should not be null");
        assertTrue(result.isEmpty(), "Result should be empty");

        // Verify interactions
        verify(admissionRepository, times(1)).findByPatientId(patientId);
    }
}