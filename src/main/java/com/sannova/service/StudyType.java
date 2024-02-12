package com.sannova.service;

import com.sannova.dto.StudyTypeListResponse;
import com.sannova.dto.TemplateDetailsByStudyIdResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface StudyType {

    List<StudyTypeListResponse> getStudyTypes();

    List<TemplateDetailsByStudyIdResponse> getTemplateDetailsByStudyId(Integer study_id);

    void createTemplate(List<MultipartFile> files,Integer studyId) throws IOException;

    void deleteTemplate(Integer studyId);
}
