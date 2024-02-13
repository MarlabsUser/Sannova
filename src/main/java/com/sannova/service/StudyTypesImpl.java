package com.sannova.service;

import com.sannova.dto.StudyTypeListResponse;
import com.sannova.dto.TemplateDetailsByStudyIdResponse;
import com.sannova.model.StudyTypes;
import com.sannova.model.TemplateDetails;
import com.sannova.repository.StudyTypesRepository;
import com.sannova.repository.TemplateDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudyTypesImpl implements StudyType{

    private final StudyTypesRepository studyTypesRepository;
    private final TemplateDetailsRepository templateDetailsRepository;

    @Override
    public List<StudyTypeListResponse> getStudyTypes() {
        List<StudyTypes> studyTypes=studyTypesRepository.findAll();
        return studyTypes.stream().map(StudyTypeListResponse::build).collect(Collectors.toList());
    }

    @Override
    public List<TemplateDetailsByStudyIdResponse> getTemplateDetailsByStudyId(Integer study_id) {
        Optional<StudyTypes> studyType=studyTypesRepository.findById(study_id);
        if(studyType.isPresent()){
            return TemplateDetailsByStudyIdResponse.getTemplateDetailsByStudyIdResponse(studyType.get());
        }
        return null;
    }

    @Override
    public void createTemplate(List<MultipartFile> files,Integer studyId) throws IOException {
        Optional<StudyTypes> studyType=studyTypesRepository.findById(studyId);
        if(studyType.isPresent()){
            for (MultipartFile file:files) {
                TemplateDetails templateDetails=
                        TemplateDetails.builder()
                        .templateName(file.getOriginalFilename())
                        .uploadedBy("Admin")
                        .data(file.getBytes())
                        .templateType(file.getContentType())
                        .cart(studyType.get())
                        .build();
            }
        }
    }

    @Override
    public void deleteTemplate(Integer templateId) {
        templateDetailsRepository.deleteById(templateId);
    }
}
