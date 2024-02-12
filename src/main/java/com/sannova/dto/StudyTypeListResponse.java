package com.sannova.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sannova.model.StudyTypes;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class StudyTypeListResponse {

    @JsonProperty(value = "study_id")
    private Integer id;
    @JsonProperty(value = "study_name")
    private String studyName;

    public static StudyTypeListResponse build(StudyTypes studyTypes){
        return StudyTypeListResponse.builder()
                .id(studyTypes.getId())
                .studyName(studyTypes.getStudyName())
                .build();
    }
}
