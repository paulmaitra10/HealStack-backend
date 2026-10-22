package org.example.Repository;

import org.example.Entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByPatientId(Long patientId);

    List<Appointment> findByPatientIdAndDoctorId(Long patientId, Long doctorId);

    List<Appointment> findByAppointmentTimeBetween(LocalDateTime startDateTime, LocalDateTime endDateTime);
}