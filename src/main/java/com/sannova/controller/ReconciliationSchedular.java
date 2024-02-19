package com.sannova.controller;

import com.sannova.model.FormPrintDetails;
import com.sannova.model.FormPrintDetailsNew;
import com.sannova.repository.FormPrintNewRepository;
import com.sannova.repository.FormPrintRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
@RequiredArgsConstructor
public class ReconciliationSchedular {

    private final FormPrintRepository formPrintRepository;
    private final FormPrintNewRepository formPrintNewRepository;
    @Scheduled(cron = "59 23 31 12 * *")
    public void archivalProcess() {

    List<FormPrintDetailsNew> formPrintDetailsNewList= new ArrayList<>();
        List<FormPrintDetails> formPrintDetails=formPrintRepository.findAll();
        for( FormPrintDetails  formPrintDetails1:formPrintDetails){
            FormPrintDetailsNew formPrintDetailsNew= new FormPrintDetailsNew();
            formPrintDetailsNew.setPrintBy(formPrintDetails1.getPrintBy());
            formPrintDetailsNew.setTemplateDetails(formPrintDetails1.getTemplateDetails());
            formPrintDetailsNew.setCreatedAt(formPrintDetails1.getCreatedAt());
            formPrintDetailsNew.setNumberOfFormsCount(formPrintDetails1.getNumberOfFormsCount());
            formPrintDetailsNew.setStudyName(formPrintDetails1.getStudyName());
            formPrintDetailsNew.setStudyId(formPrintDetails1.getStudyId());
            formPrintDetailsNewList.add(formPrintDetailsNew);
        }
        formPrintNewRepository.saveAll(formPrintDetailsNewList);

        formPrintRepository.deleteAll(formPrintDetails);

    }






}
