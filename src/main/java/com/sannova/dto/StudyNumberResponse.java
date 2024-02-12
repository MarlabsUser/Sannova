package com.sannova.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class StudyNumberResponse {

    @JsonProperty(value = "study_name")
    private String number;


}
