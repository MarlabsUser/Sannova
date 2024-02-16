package com.sannova.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sannova.model.FormPrintDetails;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FormConfirmationRequest {

 @JsonProperty(value = "studyNumber")
 private String studyNumber;
 @JsonProperty(value = "studyId")
 private Integer studyId;
 @JsonProperty(value = "templateDetails")
 private List<FromTemplateDetailsDto> studyTypeDetailsId;
 @JsonProperty(value = "username")
 private  String username;
}
