package com.sannova.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IdGeneratorTemplateDetailsResponse {
    @JsonProperty(value = "templateId")
    private Integer templateId;
    @JsonProperty(value = "template_name")
    private String templateName;
    @JsonProperty(value = "formCount")
    private Integer formCount;
    @JsonProperty(value = "first_number")
    private String firstNumber;
    @JsonProperty(value = "last_number")
    private String lastNumber;
    @JsonProperty(value = "Serial_number_list")
    private List<String> SerialNumberList;

}
