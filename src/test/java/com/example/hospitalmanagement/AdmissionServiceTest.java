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
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close(); // Ensures resources are closed after each test
    }

    @Test
    void testAdmitPatient_Success() {
        // Arrange
        AdmissionDTO admissionDTO = new AdmissionDTO(1L, "Heart Surgery");
        Patient patient = new Patient();
        patient.setId(1L);

        AdmissionState admission = new AdmissionState(patient, "Heart Surgery", LocalDateTime.now());

        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));
        when(admissionRepository.save(any(AdmissionState.class))).thenReturn(admission);

        // Act
        AdmissionState result = admissionService.admitPatient(admissionDTO);

        // Assert
        assertNotNull(result);
        assertEquals("Heart Surgery", result.getCause());
        assertEquals(patient, result.getPatient());

        verify(patientRepository, times(1)).findById(1L);
        verify(admissionRepository, times(1)).save(any(AdmissionState.class));
    }

    @Test
    void testAdmitPatient_PatientNotFound() {
        // Arrange
        AdmissionDTO admissionDTO = new AdmissionDTO(2L, "General Checkup");

        when(patientRepository.findById(2L)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> admissionService.admitPatient(admissionDTO));
        assertEquals("Patient not found", exception.getMessage());

        verify(patientRepository, times(1)).findById(2L);
        verify(admissionRepository, never()).save(any(AdmissionState.class));
    }

    @Test
    void testGetAdmissionsByPatient_Success() {
        // Arrange
        Long patientId = 1L;
        Patient patient = new Patient();
        patient.setId(patientId);

        AdmissionState admission = new AdmissionState(patient, "Heart Surgery", LocalDateTime.now());
        List<AdmissionState> admissions = Collections.singletonList(admission);

        when(admissionRepository.findByPatientId(patientId)).thenReturn(admissions);

        // Act
        List<AdmissionDTO> result = admissionService.getAdmissionsByPatient(patientId);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Heart Surgery", result.get(0).getCause());
        assertEquals(patientId, result.get(0).getPatientId());

        verify(admissionRepository, times(1)).findByPatientId(patientId);
    }

    @Test
    void testGetAdmissionsByPatient_NoAdmissionsFound() {
        // Arrange
        Long patientId = 2L;

        when(admissionRepository.findByPatientId(patientId)).thenReturn(Collections.emptyList());

        // Act
        List<AdmissionDTO> result = admissionService.getAdmissionsByPatient(patientId);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(admissionRepository, times(1)).findByPatientId(patientId);
    }
}