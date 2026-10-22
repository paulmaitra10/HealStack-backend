package org.example.DTO.DoctorDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddDoctorDTO {
    @NotBlank(message = "Name should not be null")
    @Size(min = 3, max = 50,message = "Name should be between 3 and 50 characters")
    private String name;

    @NotBlank
    @Size(min = 3, max = 50)
    private String specialization;

    @NotBlank
    @Email
    private String email;
}
