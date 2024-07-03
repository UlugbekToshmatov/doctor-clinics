package com.example.doctor_clinics.dtos.education;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EducationResponse {
    private Long docId;
    private List<EducationResponseForm> educationList;
}
