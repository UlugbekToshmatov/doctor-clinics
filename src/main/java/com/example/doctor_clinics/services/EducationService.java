package com.example.doctor_clinics.services;

import com.example.doctor_clinics.dtos.education.EducationResponse;
import com.example.doctor_clinics.dtos.education.EducationForm;

public interface EducationService {
    EducationResponse addEducationById(Long docId, EducationForm form);
}
