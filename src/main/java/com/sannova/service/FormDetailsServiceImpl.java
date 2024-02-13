package com.sannova.service;

import com.sannova.dto.StudyNumberResponse;
import com.sannova.model.FormPrintDetails;
import com.sannova.repository.FormPrintRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FormDetailsServiceImpl implements  FormDetailsService {

    private  FormPrintRepository formPrintRepository;

    @Override
    public String getStudyNumber() {
        String studyName = "ABED";
        List<FormPrintDetails> formPrintDetails = formPrintRepository.findAll();
        Integer Count = formPrintDetails.size();
        Integer Number=Count+1;
        String studyNumber=studyName+Number;
        return studyNumber;
    }
}
