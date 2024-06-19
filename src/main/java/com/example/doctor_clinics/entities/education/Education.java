package com.example.doctor_clinics.entities.education;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Education {
    private Long docId;
    private String institutionName;
    private String field;
    private String degree;
    private Date startDate;
    private Date endDate;
}
