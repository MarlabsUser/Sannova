package com.sannova.controller;

import com.sannova.dto.LoginRequest;
import com.sannova.dto.ReconciliationRequestDto;
import com.sannova.service.ReconciliationService;
import com.sannova.service.ReconsiliationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.sannova.util.URLDetails.URL_LOGIN;

@RestController
@RequiredArgsConstructor
public class ReconciliationController {

    private ReconciliationService service;

    @PostMapping(value = URL_LOGIN, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getReconciliationDetails(@RequestBody ReconciliationRequestDto request){
        return ResponseEntity.ok(service.getReconsiliationDetails(request.getSearch(),request.getFromDate(),request.getToDate()));
    }

}
