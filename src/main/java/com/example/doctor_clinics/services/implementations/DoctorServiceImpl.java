package com.example.doctor_clinics.services.implementations;

import com.example.doctor_clinics.dto_mappers.DoctorDTOmapper;
import com.example.doctor_clinics.dtos.doctor.CreateProfileForm;
import com.example.doctor_clinics.dtos.doctor.DoctorResponse;
import com.example.doctor_clinics.dtos.doctor.UpdateDoctorForm;
import com.example.doctor_clinics.entities.Doctor;
import com.example.doctor_clinics.entities.DoctorWithOldImageUrl;
import com.example.doctor_clinics.repositories.DoctorRepository;
import com.example.doctor_clinics.services.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository<Doctor> doctorRepository;
    private final MinioService minioService;


    @Override
    public DoctorResponse createProfile(MultipartFile imageFile, CreateProfileForm form) {
        // create image url
        String imageUrl = UUID.randomUUID() + "/" + imageFile.getOriginalFilename();

        // create new profile with the image url
        Doctor doctor = doctorRepository.create(DoctorDTOmapper.mapToDoctor(form, imageUrl));

        // if successful, save the image file to minio
        minioService.uploadImage(imageFile, imageUrl);

        return DoctorDTOmapper.mapToResponse(doctor);
    }

    @Override
    public DoctorResponse getById(Long id) {
        return DoctorDTOmapper.mapToResponse(doctorRepository.get(id));
    }

    @Override
    public DoctorResponse updateById(UpdateDoctorForm form, Long id) {
        return DoctorDTOmapper.mapToResponse(doctorRepository.update(DoctorDTOmapper.mapToDoctor(form, id)));
    }

    @Override
    public DoctorResponse updateImageById(Long docId, MultipartFile imageFile) {
        String newImageUrl = UUID.randomUUID() + "/" + imageFile.getOriginalFilename();
        DoctorWithOldImageUrl doctor = doctorRepository.updateProfileImageById(docId, newImageUrl);
        minioService.removeImage(doctor.getOldImageUrl());
        minioService.uploadImage(imageFile, newImageUrl);
        return DoctorDTOmapper.mapToResponse(doctor.getDoctor());
    }
}
