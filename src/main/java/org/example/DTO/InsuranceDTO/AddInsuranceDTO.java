package org.example.DTO.InsuranceDTO;


import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.Entity.Patient;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddInsuranceDTO {
    @NotBlank
    private String policyNumber;
    @NotBlank
    private String provider;
    @NotBlank
    @Future
    private LocalDate validUntil;
    @NotNull
    private Long patientId;
}
