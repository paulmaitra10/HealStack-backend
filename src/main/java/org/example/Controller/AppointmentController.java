package org.example.Controller;


import lombok.RequiredArgsConstructor;
import org.example.DTO.AppointmentDTO.AddAppointmentDTO;
import org.example.DTO.AppointmentDTO.AppointmentDTO;
import org.example.Entity.Appointment;
import org.example.Service.AppointmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/appointments")
public class AppointmentController {
    private final AppointmentService appointmentService;

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(appointmentService.getAppointmentById(id));
    }

    @GetMapping("/patient/{id}")
    public ResponseEntity<List<AppointmentDTO>> findByPatientId(@PathVariable Long id) {
        return ResponseEntity.ok(appointmentService.getAppointmentsByPatientId(id));
    }

    @GetMapping("/doctor/{id}")
    public ResponseEntity<List<AppointmentDTO>> findByDoctorId(@PathVariable Long id) {
        return ResponseEntity.ok(appointmentService.getAppointmentsByDoctorId(id));
    }

    @GetMapping("/doctor/{docId}/patient/{patientId}")
    public ResponseEntity<List<AppointmentDTO>>  findByPatientIdAndDoctorId(@PathVariable Long docId, @PathVariable Long patientId) {
        return ResponseEntity.ok(appointmentService.getAppointmentsByDoctorIdAndPatientId(docId, patientId));
    }

    @GetMapping("/start/{startTime}/end/{endTime}")
    public ResponseEntity<List<AppointmentDTO>> findByStartAndEnd(@PathVariable LocalDateTime startTime, @PathVariable LocalDateTime endTime) {
        return ResponseEntity.ok(appointmentService.getAppointmentsByAppointmentTime(startTime, endTime));
    }

    @PostMapping("")
    public ResponseEntity<AppointmentDTO> createAppointment(@RequestBody AddAppointmentDTO appointmentDTO) {
        AppointmentDTO saved = appointmentService.createAppointment(appointmentDTO);
        return ResponseEntity.created(URI.create("/"+saved.getId())).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppointmentDTO> updateAppointment(@PathVariable Long id, @RequestBody AddAppointmentDTO appointmentDTO) {
        return ResponseEntity.ok(appointmentService.updateAppointment(id, appointmentDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AppointmentDTO> deleteAppointment(@PathVariable Long id) {
        return ResponseEntity.ok(appointmentService.deleteAppointment(id));
    }
}
