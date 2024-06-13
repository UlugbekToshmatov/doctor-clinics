package com.example.doctor_clinics.entities;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DoctorWithOldImageUrl {
    private Doctor doctor;
    private String oldImageUrl;
}
