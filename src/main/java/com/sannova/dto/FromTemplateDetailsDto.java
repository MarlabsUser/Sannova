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
    @JsonProperty(value = "formCount")
    private Integer formCount;
}
