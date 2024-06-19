package com.example.doctor_clinics.controllers;

import com.example.doctor_clinics.dtos.HttpResponse;
import com.example.doctor_clinics.dtos.education.EducationResponse;
import com.example.doctor_clinics.dtos.education.EducationForm;
import com.example.doctor_clinics.services.EducationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("api/v1/doctor")
@RequiredArgsConstructor
public class EducationController {
    private final EducationService educationService;


    @PostMapping("edu/{doc-id}")
    public ResponseEntity<HttpResponse> addEducation(@PathVariable("doc-id") Long docId, @RequestBody EducationForm form) {
        EducationResponse educationResponse = educationService.addEducationById(docId, form);

        return new ResponseEntity<>(
            HttpResponse.builder()
                .timeStamp(LocalDateTime.now().toString())
                .httpStatus(HttpStatus.CREATED)
                .statusCode(HttpStatus.CREATED.value())
                .message("Educational background added successfully")
                .data(Map.of("education", educationResponse))
                .build(),
            HttpStatus.CREATED
        );

    }
}
