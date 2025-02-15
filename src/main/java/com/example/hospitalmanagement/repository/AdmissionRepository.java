package com.example.hospitalmanagement.repository;

import com.example.hospitalmanagement.entity.AdmissionState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdmissionRepository extends JpaRepository<AdmissionState, Long> {
    List<AdmissionState> findByPatientId(Long patientId);
}