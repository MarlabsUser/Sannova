package com.sannova.service;

import com.sannova.dto.StudyTypeListResponse;
import com.sannova.dto.TemplateDetailsByStudyIdResponse;
import com.sannova.model.StudyTypes;
import com.sannova.model.TemplateDetails;
import com.sannova.repository.StudyTypesRepository;
import com.sannova.repository.TemplateDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
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
    public List<TemplateDetailsByStudyIdResponse> getTemplateDetailsByStudyId(List<Integer> study_id) {
        List<TemplateDetails> templateDetailsList=templateDetailsRepository.findByStatusAndStudyTypesIdIn(true,study_id);
        if(templateDetailsList !=null && !templateDetailsList.isEmpty()){
            return TemplateDetailsByStudyIdResponse.getTemplateDetailsByStudyIdResponse(templateDetailsList);
        }
        return null;
    }

    @Override
    public void createTemplate(List<MultipartFile> files,Integer studyId) throws IOException {
        Optional<StudyTypes> studyType=studyTypesRepository.findById(studyId);
        List<TemplateDetails> templateDetailsList=new ArrayList<>();
        if(studyType.isPresent()){
            for (MultipartFile file:files) {
                templateDetailsList.add(
                        TemplateDetails.builder()
                        .templateName(file.getOriginalFilename())
                        .uploadedBy("Admin")
                        .data(file.getBytes())
                        .templateType(file.getContentType())
                        .studyTypes(studyType.get())
                        .status(true)
                        .build());

            }
        }
        templateDetailsRepository.saveAll(templateDetailsList);
    }

    @Override
    public void deleteTemplate(List<Integer> templateIds) {
        List<TemplateDetails> templateDetails=templateDetailsRepository.findAllById(templateIds);
        List<TemplateDetails> templateDetailsStatusChange=templateDetails.stream()
                .map(v->{
                    v.setStatus(false);
                    return v;
                }).collect(Collectors.toList());
        templateDetailsRepository.saveAll(templateDetailsStatusChange);
    }
}
