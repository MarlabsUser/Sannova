package com.sannova.controller;

import com.sannova.model.FormPrintDetails;
import com.sannova.model.FormPrintDetailsBackUp;
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

    List<FormPrintDetailsBackUp> formPrintDetailsBackUpList = new ArrayList<>();
        List<FormPrintDetails> formPrintDetails=formPrintRepository.findAll();
        for( FormPrintDetails  formPrintDetails1:formPrintDetails){
            FormPrintDetailsBackUp formPrintDetailsBackUp = new FormPrintDetailsBackUp();
            formPrintDetailsBackUp.setPrintBy(formPrintDetails1.getPrintBy());
            formPrintDetailsBackUp.setTemplateDetails(formPrintDetails1.getTemplateDetails());
            formPrintDetailsBackUp.setCreatedAt(formPrintDetails1.getCreatedAt());
            formPrintDetailsBackUp.setNumberOfFormsCount(formPrintDetails1.getNumberOfFormsCount());
            formPrintDetailsBackUp.setStudyName(formPrintDetails1.getStudyName());
            formPrintDetailsBackUp.setStudyId(formPrintDetails1.getStudyId());
            formPrintDetailsBackUpList.add(formPrintDetailsBackUp);
        }
        formPrintNewRepository.saveAll(formPrintDetailsBackUpList);

        formPrintRepository.deleteAll(formPrintDetails);

    }






}
