package org.example.Service;

import org.example.DTO.DoctorDTO.AddDoctorDTO;
import org.example.DTO.DoctorDTO.DoctorDTO;
import org.example.Entity.Doctor;

import java.util.List;

public interface DoctorService {

    DoctorDTO getDoctorById(Long id);

    List<DoctorDTO> getDoctorList();

    DoctorDTO createDoctor(AddDoctorDTO addDoctorDTO);

    DoctorDTO updateDoctor(Long Id,AddDoctorDTO updateDoctorDTO);

    DoctorDTO deleteDoctor(Long id);

    List<DoctorDTO> getDoctorBySpecialization(String specialization);

}
