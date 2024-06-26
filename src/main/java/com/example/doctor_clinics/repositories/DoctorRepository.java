package com.example.doctor_clinics.repositories;

import com.example.doctor_clinics.entities.doctor.Doctor;
import com.example.doctor_clinics.entities.doctor.DoctorWithOldImageUrl;

public interface DoctorRepository<T extends Doctor> {
    T create(T doctor);
    T get(Long id);
    T update(T data);
    DoctorWithOldImageUrl updateProfileImageById(Long docId, String imageUrl);
    Boolean delete(Long id);
}
