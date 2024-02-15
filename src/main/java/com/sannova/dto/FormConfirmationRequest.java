package com.sannova.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sannova.model.FormPrintDetails;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
public class FormConfirmationRequest {

 private String studyNumber;
 private Integer studyId;

 private List<Integer> studyTypeDetailsId;
  private Integer formCount;

  private  String username;




}
