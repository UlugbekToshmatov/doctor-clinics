package com.example.doctor_clinics.dto_mappers;

import com.example.doctor_clinics.dtos.doctor.CreateProfileForm;
import com.example.doctor_clinics.dtos.doctor.DoctorResponse;
import com.example.doctor_clinics.dtos.doctor.UpdateDoctorForm;
import com.example.doctor_clinics.entities.Doctor;
import org.springframework.beans.BeanUtils;

public class DoctorDTOmapper {

    public static Doctor mapToDoctor(CreateProfileForm form, String imageUrl) {
        Doctor doctor = new Doctor();
        BeanUtils.copyProperties(form, doctor);
        doctor.setImageUrl(imageUrl);
        return doctor;
    }

    public static Doctor mapToDoctor(UpdateDoctorForm form, Long id) {
        Doctor doctor = new Doctor();
        BeanUtils.copyProperties(form, doctor);
        doctor.setId(id);
        return doctor;
    }

    public static DoctorResponse mapToResponse(Doctor doctor) {
        DoctorResponse response = new DoctorResponse();
        BeanUtils.copyProperties(doctor, response);
        return response;
    }
}
