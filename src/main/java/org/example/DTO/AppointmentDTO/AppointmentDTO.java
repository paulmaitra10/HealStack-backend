package org.example.DTO.AppointmentDTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.Entity.Doctor;
import org.example.Entity.Patient;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppointmentDTO {
    private Long id;
    private String reason;
    private String status;
    private LocalDateTime appointmentTime;
    private Patient patient;
    private Doctor doctor;
}
