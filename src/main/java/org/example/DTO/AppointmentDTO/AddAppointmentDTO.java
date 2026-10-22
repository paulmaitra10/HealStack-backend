package org.example.DTO.AppointmentDTO;


import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddAppointmentDTO {
    @NotBlank
    @Size(min = 10, max = 300)
    private String reason;

    private String status;

    @NotNull
    @Future
    private LocalDateTime appointmentTime;

    @NotNull
    private Long patientId;

    @NotNull
    private Long doctorId;
}
