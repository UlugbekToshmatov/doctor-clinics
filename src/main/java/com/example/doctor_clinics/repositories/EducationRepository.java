package com.example.doctor_clinics.repositories;


import com.example.doctor_clinics.dtos.education.EducationRequestForm;
import com.example.doctor_clinics.entities.education.Education;

import java.util.List;

public interface EducationRepository<T extends Education> {

    List<T> addEducationHistoryByDocId(Long docId, EducationRequestForm form);
    List<T> getEducationalHistoriesByDocId(Long docId);
}
