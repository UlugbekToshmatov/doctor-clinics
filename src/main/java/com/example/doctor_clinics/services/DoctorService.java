package com.example.doctor_clinics.services;

import com.example.doctor_clinics.dtos.doctor.CreateProfileForm;
import com.example.doctor_clinics.dtos.doctor.DoctorResponse;
import com.example.doctor_clinics.dtos.doctor.UpdateDoctorForm;
import org.springframework.web.multipart.MultipartFile;

public interface DoctorService {
    DoctorResponse createProfile(MultipartFile imageFile, CreateProfileForm form);
    DoctorResponse getById(Long id);
    DoctorResponse updateById(UpdateDoctorForm form, Long id);
    DoctorResponse updateImageById(Long docId, MultipartFile imageFile);
}
