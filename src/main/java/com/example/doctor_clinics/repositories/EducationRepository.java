package com.example.doctor_clinics.repositories;


import com.example.doctor_clinics.dtos.education.EducationForm;
import com.example.doctor_clinics.entities.education.Education;

import java.util.List;

public interface EducationRepository<T extends Education> {

    List<T> addEducationHistoryById(Long docId, EducationForm form);
}
