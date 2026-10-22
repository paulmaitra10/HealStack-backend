package org.example.DTO.PatientDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.Entity.Type.BloodGroupType;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddPatientDTO {
    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 50, message = "Name should be between 3 and 50 characters")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Gender is required")
    private String gender;

    @NotNull(message = "BirthDate is required")
    @Past(message = "BirthDate must be in the past")
    private LocalDate birthDate;

    private BloodGroupType bloodGroupType;
}