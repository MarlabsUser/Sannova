package com.sannova.service;

import com.sannova.dto.StudyNumberResponse;
import com.sannova.repository.FormPrintRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FormDetailsServiceImpl implements  FormDetailsService {

    private final FormPrintRepository formPrintRepository;

    @Override
    public StudyNumberResponse getStudyNumber() {
        String studyNumber="ABED123";
        return null;
    }
}
