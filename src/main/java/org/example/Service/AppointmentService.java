package org.example.Service;

import org.example.DTO.AppointmentDTO.AddAppointmentDTO;
import org.example.DTO.AppointmentDTO.AppointmentDTO;
import org.example.Entity.Appointment;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentService {

    AppointmentDTO createAppointment(AddAppointmentDTO addAppointmentDTO);

    AppointmentDTO getAppointmentById(Long id);

    AppointmentDTO updateAppointment(Long id,AddAppointmentDTO addAppointmentDTO);

    AppointmentDTO deleteAppointment(Long id);

    List<AppointmentDTO> getAppointmentsByPatientId(Long id);

    List<AppointmentDTO> getAppointmentsByDoctorId(Long id);

    List<AppointmentDTO> getAppointmentsByDoctorIdAndPatientId(Long doctorId, Long patientId);

    List<AppointmentDTO> getAppointmentsByAppointmentTime(LocalDateTime startDateTime, LocalDateTime endDateTime);
}
