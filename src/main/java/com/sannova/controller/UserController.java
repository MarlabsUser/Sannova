package com.sannova.controller;

import com.sannova.dto.StudyNumberResponse;

import com.sannova.dto.StudyTypeListResponse;
import com.sannova.service.FormDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

import static com.sannova.util.URLDetails.URL_STUDY_NUMBER;
import static com.sannova.util.URLDetails.URL_STUDY_TYPES;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final FormDetailsService formDetailsService;

    @GetMapping(value = URL_STUDY_NUMBER, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity  getUserDetails(){
        return ResponseEntity.ok(formDetailsService.getStudyNumber());
    }
}
