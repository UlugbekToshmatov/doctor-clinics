package com.example.doctor_clinics.entities;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Doctor {
    private Long id;
    private String pinfl;
    private String firstName;
    private String lastName;
    private String patronymic;
    private Date dateOfBirth;
    private String imageUrl;
    private String scientificDegree;
    private String phoneNumber;
    private String email;
    private String telegram;
    private Date createdDate;
    private Date modifiedDate;
}
