package com.example.doctor_clinics.services.implementations;

import com.example.doctor_clinics.dtos.education.EducationResponseForm;
import com.example.doctor_clinics.dtos.education.EducationRequestForm;
import com.example.doctor_clinics.dtos.education.EducationResponse;
import com.example.doctor_clinics.entities.education.Education;
import com.example.doctor_clinics.repositories.EducationRepository;
import com.example.doctor_clinics.services.EducationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EducationServiceImpl implements EducationService {
    private final EducationRepository<Education> educationRepository;

    @Override
    public EducationResponse addEducationByDocId(Long docId, EducationRequestForm form) {
        List<Education> educationList = educationRepository.addEducationHistoryByDocId(docId, form);
        return makeEducationResponse(docId, educationList);
    }

    @Override
    public EducationResponse getEducationalHistoriesByDocId(Long docId) {
        List<Education> educationList = educationRepository.getEducationalHistoriesByDocId(docId);
        return makeEducationResponse(docId, educationList);
    }

    private EducationResponse makeEducationResponse(Long docId, List<Education> educationList) {
        return EducationResponse.builder()
            .educationList(educationList.stream().map(
                education -> EducationResponseForm.builder()
                    .eduId(education.getEduId())
                    .institutionName(education.getInstitutionName())
                    .specId(education.getSpecId())
                    .field(education.getField())
                    .degree(education.getDegree())
                    .startDate(education.getStartDate())
                    .endDate(education.getEndDate())
                    .build()
                ).toList()
            )
            .docId(docId)
            .build();
    }
}
