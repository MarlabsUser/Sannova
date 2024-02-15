package com.sannova.service;

import com.sannova.dto.FormConfirmationRequest;
import com.sannova.model.FormPrintDetails;
import com.sannova.model.StudyTypes;
import com.sannova.model.TemplateDetails;
import com.sannova.repository.FormPrintRepository;
import com.sannova.repository.StudyTypesRepository;
import com.sannova.repository.TemplateDetailsRepository;
import com.sannova.util.ZipConvert;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    public String addFormConfirmationDetails(FormConfirmationRequest request) throws IOException {
        Optional<StudyTypes> studyTypes= studyTypesRepository.findById(request.getStudyId());
        List<FormPrintDetails> formPrintDetailsList=new ArrayList<>();
        for(Integer studyTypeDetailsId :request.getStudyTypeDetailsId()){
            Optional<TemplateDetails> templateDetails=templateDetailsRepository.findById(studyTypeDetailsId);
            if(templateDetails.isPresent()){
                TemplateDetails templateDetails1=templateDetails.get();
                if(studyTypes.isPresent()){
                    StudyTypes studyTypes1=   studyTypes.get();
                    FormPrintDetails formPrintDetails= new FormPrintDetails();
                    formPrintDetails.setStudyId(studyTypes1);
                    formPrintDetails.setCreatedAt(LocalDateTime.now());
                    formPrintDetails.setTemplateDetails(templateDetails1);
                    formPrintDetails.setNumberOfFormsCount(request.getFormCount());
                    formPrintDetails.setPrintBy(request.getUsername());
                    formPrintDetails.setUpdatedAt(LocalDateTime.now());
                   formPrintDetailsList.add(formPrintDetails);

                }


            }

        }
      List<FormPrintDetails> formPrintDetails= formPrintRepository.saveAll(formPrintDetailsList);
        List<byte[]> bytes= new ArrayList<>();
        for(FormPrintDetails formPrintDetails1:formPrintDetails){
            byte[] bytes1=formPrintDetails1.getTemplateDetails().getData();
            bytes.add(bytes1);
        }
        String filename =request.getStudyNumber();
        ZipConvert.zipBytes(filename,bytes);



        return "Download Successfully ";
    }
}
