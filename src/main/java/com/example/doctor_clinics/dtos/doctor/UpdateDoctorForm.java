package com.example.doctor_clinics.dtos.doctor;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateDoctorForm {
    @NotEmpty(message = "First name cannot be empty")
    private String firstName;
    @NotEmpty(message = "Last name cannot be empty")
    private String lastName;
    private String patronymic;
    private String scientificDegree;
    @NotEmpty(message = "Phone number cannot be empty")
    private String phoneNumber;
    private String email;
    private String telegram;
}
