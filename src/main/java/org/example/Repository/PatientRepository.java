package org.example.Repository;

import org.example.Entity.Type.BloodGroupType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.example.Entity.Patient;

import java.time.LocalDate;
import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> findByName(String name);

    List<Patient> findByGender(String gender);

    List<Patient> findByBirthDateGreaterThan(LocalDate dob);

    List<Patient> findByBloodGroupType(BloodGroupType bloodGroupType);
}
