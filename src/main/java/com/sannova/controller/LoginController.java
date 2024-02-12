package com.sannova.controller;

import com.sannova.dto.LoginRequest;
import com.sannova.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.sannova.util.URLDetails.URL_login;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService service;

    @PostMapping(value = URL_login, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getLoginDetails(@RequestBody LoginRequest request){
        return ResponseEntity.ok(service.login(request));
    }
}
