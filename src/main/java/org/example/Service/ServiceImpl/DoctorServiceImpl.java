package org.example.Service.ServiceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.DTO.DoctorDTO.AddDoctorDTO;
import org.example.DTO.DoctorDTO.DoctorDTO;
import org.example.Entity.Appointment;
import org.example.Entity.Doctor;
import org.example.Repository.DoctorRepository;
import org.example.Service.DoctorService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public DoctorDTO getDoctorById(Long id) {
        Doctor doctor = doctorRepository.findById(id).orElseThrow();
        return modelMapper.map(doctor,DoctorDTO.class);
    }

    @Override
    @Transactional
    public List<DoctorDTO> getDoctorList() {
        List<Doctor> doctors = doctorRepository.findAll();
        return getDoctorDTOList(doctors);
    }

    @Override
    @Transactional
    public DoctorDTO createDoctor(AddDoctorDTO addDoctorDTO) {
        Doctor doctor = modelMapper.map(addDoctorDTO,Doctor.class);
        doctorRepository.save(doctor);
        return modelMapper.map(doctor,DoctorDTO.class);
    }

    @Override
    @Transactional
    public DoctorDTO updateDoctor(Long Id,AddDoctorDTO updateDoctorDTO) {
        Doctor doctor = doctorRepository.findById(Id).orElseThrow();

        doctor.setName(updateDoctorDTO.getName());
        doctor.setEmail(updateDoctorDTO.getEmail());
        doctor.setSpecialization(updateDoctorDTO.getSpecialization());
        doctorRepository.save(doctor);
        return modelMapper.map(doctor,DoctorDTO.class);
    }

    @Override
    @Transactional
    public DoctorDTO deleteDoctor(Long id) {
        Doctor doctor = doctorRepository.findById(id).orElseThrow();
        doctorRepository.delete(doctor);
        return modelMapper.map(doctor,DoctorDTO.class);
    }

    @Override
    @Transactional
    public List<DoctorDTO> getDoctorBySpecialization(String specialization) {
        List<Doctor> doctors = doctorRepository.findBySpecialization(specialization);
        return getDoctorDTOList(doctors);
    }

    private List<DoctorDTO> getDoctorDTOList(List<Doctor> doctors) {
        List<DoctorDTO> doctorDTOList = new ArrayList<>();
        for (Doctor doctor : doctors) {
            DoctorDTO doctorDTO = modelMapper.map(doctor,DoctorDTO.class);
        }
        return doctorDTOList;
    }
}
