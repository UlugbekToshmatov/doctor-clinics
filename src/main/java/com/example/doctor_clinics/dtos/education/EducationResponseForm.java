package com.example.doctor_clinics.dtos.education;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EducationResponseForm {
    private Long eduId;
    private String institutionName;
    private Long specId;
    private String field;
    private String degree;
    private Date startDate;
    private Date endDate;
}