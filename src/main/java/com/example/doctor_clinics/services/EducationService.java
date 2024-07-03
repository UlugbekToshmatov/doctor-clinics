package com.example.doctor_clinics.services;

import com.example.doctor_clinics.dtos.education.EducationRequestForm;
import com.example.doctor_clinics.dtos.education.EducationResponse;

public interface EducationService {
    EducationResponse addEducationByDocId(Long docId, EducationRequestForm form);
    EducationResponse getEducationalHistoriesByDocId(Long docId);
}
