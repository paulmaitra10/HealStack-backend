package org.example.DTO.PatientDTO;

import lombok.Data;
import org.example.Entity.Type.BloodGroupType;

import java.time.LocalDate;

@Data
public class PatientDTO {
    private Long id;
    private String name;
    private String gender;
    private LocalDate birthDate;
    private BloodGroupType bloodGroupType;
}
