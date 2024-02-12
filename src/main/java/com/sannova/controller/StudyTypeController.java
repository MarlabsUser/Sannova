package com.sannova.controller;

import com.sannova.dto.StudyTypeListResponse;
import com.sannova.dto.TemplateDetailsByStudyIdResponse;
import com.sannova.service.StudyType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static com.sannova.util.URLDetails.*;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
@RequiredArgsConstructor
public class StudyTypeController {

    private StudyType studyType;
    @GetMapping(value = URL_STUDY_TYPES, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StudyTypeListResponse>> getStudyTypes(){
        return ResponseEntity.ok(studyType.getStudyTypes());
    }

    @GetMapping(value = URL_TEMPLATE_DETAILS_BY_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TemplateDetailsByStudyIdResponse>> getTemplateDetails(@PathVariable Integer study_id){
        return ResponseEntity.ok(studyType.getTemplateDetailsByStudyId(study_id));
    }

    @PostMapping(value =URL_UPLOAD_TEMPLATE, produces = MediaType.APPLICATION_JSON_VALUE, consumes = {MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity publishTemplate(@RequestParam("file") List<MultipartFile> files,
                                                  @RequestParam("study_id") Integer StudyId) throws IOException {
        studyType.createTemplate(files,StudyId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping(value =URL_DELETE_TEMPLATE_BY_TEMPLATE_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteTemplate(@PathVariable Integer template_id) throws IOException {
        studyType.deleteTemplate(template_id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
