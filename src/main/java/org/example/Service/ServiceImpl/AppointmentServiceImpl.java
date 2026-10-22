package org.example.Service.ServiceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.DTO.AppointmentDTO.AddAppointmentDTO;
import org.example.DTO.AppointmentDTO.AppointmentDTO;
import org.example.Entity.Appointment;
import org.example.Entity.Doctor;
import org.example.Entity.Patient;
import org.example.Repository.AppointmentRepository;
import org.example.Repository.DoctorRepository;
import org.example.Repository.PatientRepository;
import org.example.Service.AppointmentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final ModelMapper modelMapper;


    @Override
    @Transactional
    public AppointmentDTO createAppointment(AddAppointmentDTO addAppointmentDTO) {
        Doctor doctor = doctorRepository.findById(addAppointmentDTO.getDoctorId()).orElseThrow();
        Patient patient = patientRepository.findById(addAppointmentDTO.getPatientId()).orElseThrow();

        Appointment appointment = Appointment
                .builder()
                .reason(addAppointmentDTO.getReason())
                .status(addAppointmentDTO.getStatus())
                .appointmentTime(addAppointmentDTO.getAppointmentTime())
                .patient(patient)
                .doctor(doctor)
                .build();

        Appointment savedAppointment = appointmentRepository.save(appointment);

        return modelMapper.map(savedAppointment, AppointmentDTO.class);

    }

    @Override
    public AppointmentDTO getAppointmentById(Long id) {
        Appointment appointment = appointmentRepository.findById(id).orElseThrow();
        return modelMapper.map(appointment, AppointmentDTO.class);
    }

    @Override
    @Transactional
    public AppointmentDTO updateAppointment(Long id, AddAppointmentDTO addAppointmentDTO) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        Doctor doctor = doctorRepository.findById(addAppointmentDTO.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        Patient patient = patientRepository.findById(addAppointmentDTO.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        appointment.setAppointmentTime(addAppointmentDTO.getAppointmentTime());
        appointment.setStatus(addAppointmentDTO.getStatus());
        appointment.setReason(addAppointmentDTO.getReason());
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);

        Appointment updated = appointmentRepository.save(appointment);

        return modelMapper.map(updated, AppointmentDTO.class);
    }

    @Override
    public AppointmentDTO deleteAppointment(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
        appointmentRepository.delete(appointment);
        return modelMapper.map(appointment, AppointmentDTO.class);
    }

    @Override
    public List<AppointmentDTO> getAppointmentsByPatientId(Long patientId) {
        List<Appointment> appointments = appointmentRepository.findByPatientId(patientId);
        return getAppointmentDTOList(appointments);
    }

    @Override
    public List<AppointmentDTO> getAppointmentsByDoctorId(Long doctorId) {
        List<Appointment> appointments = appointmentRepository.findByPatientId(doctorId);
        return getAppointmentDTOList(appointments);
    }

    @Override
    public List<AppointmentDTO> getAppointmentsByDoctorIdAndPatientId(Long doctorId, Long patientId) {
        List<Appointment> appointments = appointmentRepository.findByPatientIdAndDoctorId(patientId,doctorId);
        return getAppointmentDTOList(appointments);
    }

    @Override
    public List<AppointmentDTO> getAppointmentsByAppointmentTime(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        List<Appointment> appointments = appointmentRepository.findByAppointmentTimeBetween(startDateTime,endDateTime);
        return getAppointmentDTOList(appointments);
    }


    private List<AppointmentDTO> getAppointmentDTOList(List<Appointment> appointments) {
        List<AppointmentDTO> appointmentDTOList = new ArrayList<>();
        for (Appointment appointment : appointments) {
            AppointmentDTO appointmentDTO = modelMapper.map(appointment, AppointmentDTO.class);
        }
        return appointmentDTOList;
    }
}
