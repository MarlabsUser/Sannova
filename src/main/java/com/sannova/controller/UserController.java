package com.sannova.controller;

import com.sannova.dto.StudyNumberResponse;

import com.sannova.service.FormDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import static com.sannova.util.URLDetails.URL_STUDY_NUMBER;

@RestController
@RequiredArgsConstructor
public class UserController {

    private FormDetailsService formDetailsService;

    @GetMapping(value = URL_STUDY_NUMBER)
    public ResponseEntity<String> getStudyNumber(){
        return ResponseEntity.ok(formDetailsService.getStudyNumber());
    }
}
