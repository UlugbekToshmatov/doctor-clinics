package com.example.doctor_clinics.dtos.doctor;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class CreateProfileForm {
    @NotEmpty(message = "PINFL (Personal Identification Number) cannot be empty")
    @NumberFormat(pattern = "########")
    private String pinfl;
    @NotEmpty(message = "Name cannot be empty")
    private String firstName;
    @NotEmpty(message = "Surname cannot be empty")
    private String lastName;
    private String patronymic;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Past(message = "Date of birth must be in the past")
    private Date dateOfBirth;
    private String scientificDegree;
    @NotEmpty(message = "Phone number cannot be empty")
    private String phoneNumber;
    private String email;
    private String telegram;
}