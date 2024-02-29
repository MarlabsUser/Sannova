package com.sannova.controller;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.sannova.dto.ReconciliationRequestDto;
import com.sannova.dto.ReconciliationResponseDto;
import com.sannova.service.ReconciliationService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.List;
import java.util.zip.DataFormatException;

import static com.sannova.util.URLDetails.*;

@RestController
@RequiredArgsConstructor
public class ReconciliationController {

    private final ReconciliationService service;

    @PostMapping(value = URL_RECONSILIATION)
    public ResponseEntity<List<ReconciliationResponseDto>> getReconciliationDetails(@RequestBody ReconciliationRequestDto request){
        return ResponseEntity.ok(service.getReconsiliationDetails(request.getSerialNumber(),request.getFromDate(),request.getToDate()));
    }

//    @PostMapping(value = URL_RECONSILIATION_PRINT)
//    public ResponseEntity<Resource> printReconciliationDetails(@RequestBody List<ReconciliationResponseDto> request) throws IOException {
//        byte[] finalByte= service.printReconsiliationDetails(request);
//        ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(finalByte);
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.parseMediaType("application/zip"));
//        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
//        String dateFormat=LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss"));
//        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachments; filename=Reconsiliation"+ dateFormat+".zip");
//        return new ResponseEntity(new InputStreamResource(byteArrayInputStream), headers, HttpStatus.OK);
//    }

    @PostMapping(URL_RECONSILIATION_PRINT)
    public ResponseEntity<String> getAllReconciliationDetails(@RequestBody List<ReconciliationResponseDto> request) throws IOException {
        byte[] excelContent = service.getReconciliationDetailsDownload(request);
        ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(excelContent);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/zip"));
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        String dateFormat=LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss"));
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachments; filename=Reconsiliation"+ dateFormat+".xls");
        return new ResponseEntity(new InputStreamResource(byteArrayInputStream), headers, HttpStatus.OK);
    }


}
