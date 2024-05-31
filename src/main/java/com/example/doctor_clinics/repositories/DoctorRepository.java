package com.example.doctor_clinics.repositories;

import com.example.doctor_clinics.entities.Doctor;

public interface DoctorRepository<T extends Doctor> {
    T create(T doctor);
    T get(Long id);
    T update(T data);
    Boolean delete(Long id);
}
