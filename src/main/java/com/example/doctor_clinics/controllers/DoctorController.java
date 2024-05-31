package com.example.doctor_clinics.controllers;

import com.example.doctor_clinics.dtos.doctor.CreateProfileForm;
import com.example.doctor_clinics.dtos.doctor.DoctorResponse;
import com.example.doctor_clinics.dtos.doctor.UpdateDoctorForm;
import com.example.doctor_clinics.services.DoctorService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1/doctor")
@AllArgsConstructor
public class DoctorController {
    private final DoctorService doctorService;


    @PostMapping
//    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<DoctorResponse> createProfile(@RequestPart("image-file") MultipartFile imageFile, @RequestPart("form") @Valid CreateProfileForm form) {
        return new ResponseEntity<>(doctorService.createProfile(imageFile, form), HttpStatus.CREATED);
    }

    @GetMapping("{doc-id}")
    public ResponseEntity<DoctorResponse> getById(@PathVariable("doc-id") Long id) {
        return new ResponseEntity<>(doctorService.getById(id), HttpStatus.OK);
    }

    @PatchMapping("{doc-id}")
    public ResponseEntity<DoctorResponse> updateById(@RequestBody UpdateDoctorForm form, @PathVariable("doc-id") Long id) {
        return new ResponseEntity<>(doctorService.updateById(form, id), HttpStatus.OK);
    }
}
