package com.example.hospitalmanagement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private LocalDate birthDate;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AdmissionState> admissions;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ClinicalData> clinicalRecords;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    public Patient() {}

    public Patient(String name, String lastName, LocalDate birthDate, Department department) {
        this.name = name;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.department = department;
    }
}
