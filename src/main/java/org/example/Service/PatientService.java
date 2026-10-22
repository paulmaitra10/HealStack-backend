package org.example.Service;

import org.example.DTO.PatientDTO.AddPatientDTO;
import org.example.DTO.PatientDTO.PatientDTO;
import org.example.Entity.Type.BloodGroupType;

import java.time.LocalDate;
import java.util.List;

public interface PatientService{
    PatientDTO getPatientById(Long id);

    PatientDTO createPatient(AddPatientDTO addPatientDTO);

    PatientDTO updatePatient(Long id, AddPatientDTO addPatientDTO);

    PatientDTO deletePatient(Long id);

    List<PatientDTO> getAllPatients();

    List<PatientDTO> getPatientByName(String name);

    List<PatientDTO> getPatientByGender(String gender);

    List<PatientDTO> getPatientByDOBGreaterThan(LocalDate dob);

    List<PatientDTO> getPatientByBloodType(BloodGroupType bloodGroupType);
}
