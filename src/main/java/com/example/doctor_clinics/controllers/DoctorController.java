package com.example.doctor_clinics.controllers;

import com.example.doctor_clinics.dtos.HttpResponse;
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

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("api/v1/doctor")
@AllArgsConstructor
public class DoctorController {
    private final DoctorService doctorService;


    @PostMapping
    public ResponseEntity<HttpResponse> createProfile(@RequestPart("image-file") MultipartFile imageFile, @RequestPart("form") @Valid CreateProfileForm form) {
        DoctorResponse doctorResponse = doctorService.createProfile(imageFile, form);
        String responseMessage = "New profile for Doctor " +
            doctorResponse.getFirstName() + " " + doctorResponse.getLastName() + " created successfully";

        return new ResponseEntity<>(
            HttpResponse.builder()
                .timeStamp(doctorResponse.getCreatedDate().toString())
                .httpStatus(HttpStatus.CREATED)
                .statusCode(HttpStatus.CREATED.value())
                .message(responseMessage)
                .data(Map.of("doctor", doctorResponse))
                .build(),
            HttpStatus.CREATED
        );
    }

    @GetMapping("{doc-id}")
    public ResponseEntity<HttpResponse> getById(@PathVariable("doc-id") Long id) {
        DoctorResponse doctorResponse = doctorService.getById(id);

        return ResponseEntity.ok(
            HttpResponse.builder()
                .timeStamp(LocalDateTime.now().toString())
                .httpStatus(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .message("Profile retrieved")
                .data(Map.of("doctor", doctorResponse))
                .build()
        );
    }

    @PatchMapping("{doc-id}")
    public ResponseEntity<HttpResponse> updateById(@RequestBody UpdateDoctorForm form, @PathVariable("doc-id") Long id) {
        DoctorResponse doctorResponse = doctorService.updateById(form, id);

        return ResponseEntity.ok(
            HttpResponse.builder()
                .timeStamp(doctorResponse.getModifiedDate().toString())
                .httpStatus(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .message("Profile updated successfully")
                .data(Map.of("doctor", doctorResponse))
                .build()
        );
    }

    @PatchMapping("update/image/{doc-id}")
    public ResponseEntity<HttpResponse> updateProfileImageById(@PathVariable("doc-id") Long docId, @RequestParam("image-file") MultipartFile imageFile) {
        DoctorResponse doctorResponse = doctorService.updateImageById(docId, imageFile);

        return ResponseEntity.ok(
            HttpResponse.builder()
                .timeStamp(doctorResponse.getModifiedDate().toString())
                .httpStatus(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .message("Profile updated successfully")
                .data(Map.of("doctor", doctorResponse))
                .build()
        );
    }
}
