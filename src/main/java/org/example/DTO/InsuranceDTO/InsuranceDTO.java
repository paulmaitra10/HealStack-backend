package org.example.DTO.InsuranceDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.Entity.Patient;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InsuranceDTO {
    private Long id;
    private String policyNumber;
    private String provider;
    private LocalDate validUntil;
    private String patientId;
}
