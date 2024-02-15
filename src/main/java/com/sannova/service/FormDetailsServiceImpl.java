package com.sannova.service;

import com.sannova.dto.FormConfirmationDetails;
import com.sannova.model.FormPrintDetails;
import com.sannova.model.StudyTypes;
import com.sannova.model.TemplateDetails;
import com.sannova.repository.FormPrintRepository;
import com.sannova.repository.StudyTypesRepository;
import com.sannova.repository.TemplateDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FormDetailsServiceImpl implements FormDetailsService{

    private final FormPrintRepository formPrintRepository;
    private  final TemplateDetailsRepository templateDetailsRepository;
    private  final StudyTypesRepository studyTypesRepository;

    @Override
    public String getStudyNumber() {
        String studyName = "SV";
        String date = String.valueOf(LocalDateTime.now().getYear()).substring(2);
        List<FormPrintDetails> formPrintDetails = formPrintRepository.findAll();
        Integer Count = formPrintDetails.size();
        Integer Number=Count+1;
        String studyNumber=studyName+Number+date;
        return studyNumber;
    }

    @Override
    public String addFormConfirmationDetails(FormConfirmationDetails request) {
        Optional<StudyTypes> studyTypes= studyTypesRepository.findById(request.getStudyId());
        if(studyTypes.isPresent()){
            StudyTypes studyTypes1=   studyTypes.get();
            FormPrintDetails formPrintDetails= new FormPrintDetails();
            formPrintDetails.setStudyId(studyTypes1);
            formPrintDetails.setCreatedAt(LocalDateTime.now());

        }


        return null;
    }
}
