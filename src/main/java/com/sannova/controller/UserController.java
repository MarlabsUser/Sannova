package com.sannova.controller;


import com.sannova.dto.FormConfirmationRequest;
import com.sannova.service.FormDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import java.io.ByteArrayInputStream;
import java.io.IOException;

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
    public ResponseEntity<Resource> AddFormConfirmationDetails(@RequestBody FormConfirmationRequest request) throws IOException {
        byte[] finalByte= formDetailsService.addFormConfirmationDetails(request);
        ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(finalByte);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/zip"));
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachments; filename="+request.getStudyId()+".zip");
        return new ResponseEntity<Resource>(new InputStreamResource(byteArrayInputStream), headers, HttpStatus.OK);
    }


}
