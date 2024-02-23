package com.sannova.service;

import com.sannova.dto.FormConfirmationRequest;
import com.sannova.dto.FromTemplateDetailsDto;
import com.sannova.dto.ZipFormattedFiles;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FormDetailsServiceImpl implements FormDetailsService{

    private final FormPrintRepository formPrintRepository;
    private  final TemplateDetailsRepository templateDetailsRepository;
    private  final StudyTypesRepository studyTypesRepository;

    @Override
    public String getStudyNumber(Integer study_id) {
        Optional<FormPrintDetails> formPrintDetails=formPrintRepository.findTopByStudyIdIdAndCreatedAtGreaterThanEqual(study_id,LocalDate.now());
        if(formPrintDetails.isPresent()){
            return formPrintDetails.get().getStudyName();
        }else{
            String studyName = "SV";
            String date = String.valueOf(LocalDateTime.now().getYear());
            Integer count = Math.toIntExact(formPrintRepository.count());
            Integer Number=count+1;
            String studyNumber=studyName+Number+date;
            return studyNumber;
        }
    }

    @Override
    public byte[] addFormConfirmationDetails(FormConfirmationRequest request) throws IOException {
        List<FormPrintDetails> formPrintDetailsList=new ArrayList<>();
        Optional<StudyTypes> studyTypes=Optional.empty();
        for(FromTemplateDetailsDto template :request.getStudyTypeDetailsId()) {
            Optional<FormPrintDetails> formPrintDetails=formPrintRepository.findByStudyNameAndTemplateDetailsId(request.getStudyNumber(),template.getTemplateId());
            if(formPrintDetails.isPresent()){
                formPrintDetails.get().setNumberOfFormsCount(formPrintDetails.get().getNumberOfFormsCount()+template.getFormCount());
                formPrintDetailsList.add(formPrintDetails.get());
            }else{
                if(!studyTypes.isPresent()){
                    studyTypes= studyTypesRepository.findById(request.getStudyId());
                }
                Optional<TemplateDetails> templateDetails=templateDetailsRepository.findById(template.getTemplateId());
                if(templateDetails.isPresent()){
                    TemplateDetails templateDetails1=templateDetails.get();
                    if(studyTypes.isPresent()){
                        StudyTypes studyTypes1=   studyTypes.get();
                        FormPrintDetails formPrint= new FormPrintDetails();
                        formPrint.setStudyName(request.getStudyNumber());
                        formPrint.setStudyId(studyTypes1);
                        formPrint.setTemplateDetails(templateDetails1);
                        formPrint.setNumberOfFormsCount(template.getFormCount());
                        formPrint.setPrintBy(request.getUsername());
                        formPrintDetailsList.add(formPrint);
                    }
                }

            }
        }
      List<FormPrintDetails> formPrintDetails= formPrintRepository.saveAll(formPrintDetailsList);
      List<ZipFormattedFiles> zipFormattedFiles=formPrintDetails.stream().map(v->ZipConvert.formattedZipArrayOfFiles(v.getTemplateDetails().getTemplateName(), v.getTemplateDetails().getData())).collect(Collectors.toList());
      return ZipConvert.zipBytes(zipFormattedFiles);
    }
}