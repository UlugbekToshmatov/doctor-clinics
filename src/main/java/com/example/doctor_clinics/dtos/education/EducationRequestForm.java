package com.example.doctor_clinics.dtos.education;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EducationRequestForm {
    private String institutionName;
    private String field;
    private String degree;
    private Date startDate;
    private Date endDate;
}
