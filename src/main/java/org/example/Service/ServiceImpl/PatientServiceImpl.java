package org.example.Service.ServiceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.DTO.PatientDTO.AddPatientDTO;
import org.example.DTO.PatientDTO.PatientDTO;
import org.example.Entity.Patient;
import org.example.Entity.Type.BloodGroupType;
import org.example.Repository.PatientRepository;
import org.example.Service.PatientService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public PatientDTO getPatientById(Long id) {
        Patient patient = patientRepository.findById(id).orElseThrow();
        return modelMapper.map(patient, PatientDTO.class);
    }

    @Override
    @Transactional
    public PatientDTO createPatient(AddPatientDTO addPatientDTO) {
        Patient patient = modelMapper.map(addPatientDTO, Patient.class);
        Patient newPatient = patientRepository.save(patient);
        return modelMapper.map(newPatient, PatientDTO.class);
    }

    @Override
    @Transactional
    public PatientDTO updatePatient(Long id, AddPatientDTO addPatientDTO) {
        Patient patient = patientRepository.findById(id).orElseThrow();
        modelMapper.map(addPatientDTO, patient);
        return modelMapper.map(patientRepository.save(patient), PatientDTO.class);
    }

    @Override
    @Transactional
    public PatientDTO deletePatient(Long id) {
        Patient patient = patientRepository.findById(id).orElseThrow();
        patientRepository.delete(patient);
        return modelMapper.map(patient, PatientDTO.class);
    }

    @Override
    @Transactional
    public List<PatientDTO> getAllPatients() {
        List<Patient> patients = patientRepository.findAll();
        return getPatientDTOs(patients);
    }

    @Override
    @Transactional
    public List<PatientDTO> getPatientByName(String name) {
        List<Patient> patients = patientRepository.findByName(name);
        return getPatientDTOs(patients);
    }

    @Override
    @Transactional
    public List<PatientDTO> getPatientByGender(String gender) {
        List<Patient> patients = patientRepository.findByGender(gender);
        return getPatientDTOs(patients);
    }

    @Override
    public List<PatientDTO> getPatientByDOBGreaterThan(LocalDate dob) {
        List<Patient> patients = patientRepository.findByBirthDateGreaterThan(dob);
        return getPatientDTOs(patients);
    }

    @Override
    public List<PatientDTO> getPatientByBloodType(BloodGroupType bloodGroupType) {
        List<Patient> patients = patientRepository.findByBloodGroupType(bloodGroupType);
        return getPatientDTOs(patients);
    }

    private List<PatientDTO>  getPatientDTOs(List<Patient> patients) {
        List<PatientDTO> patientDTOs = new ArrayList<>();
        for (Patient patient : patients) {
            patientDTOs.add(modelMapper.map(patient, PatientDTO.class));
        }
        return patientDTOs;
    }
}
