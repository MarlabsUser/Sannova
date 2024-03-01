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
public class FromTemplateDetailsDto {
    @JsonProperty(value = "templateId")
    private Integer templateId;
    @JsonProperty(value = "template_name")
    private String templateName;
    @JsonProperty(value = "formCount")
    private Integer formCount;
    @JsonProperty(value = "first__serial_number")
    private String firstSerialNumber;
    @JsonProperty(value = "last_serial_number")
    private String lastSerialNumber;
    @JsonProperty(value = "Serial_number_list")
    private List<String> SerialNumberList;
    @JsonProperty(value = "starting_serial_number")
    private Integer startingSerialNumber;

}
