package com.sannova.controller;


import com.sannova.dto.FormConfirmationRequest;
import com.sannova.service.FormDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import static com.sannova.util.URLDetails.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final FormDetailsService formDetailsService;

    @GetMapping(value = URL_STUDY_NUMBER, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity  getUserDetails(){
        return ResponseEntity.ok(formDetailsService.getStudyNumber());
    }

    @PostMapping(value = URL_FORM_CONFIRMATION_DETAILS)
    public ResponseEntity AddFormConfirmationDetails(@RequestBody FormConfirmationRequest request){
        return ResponseEntity.ok(formDetailsService.addFormConfirmationDetails(request));
    }


}
