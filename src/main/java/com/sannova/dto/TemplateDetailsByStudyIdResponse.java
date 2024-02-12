package com.sannova.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sannova.model.StudyTypes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TemplateDetailsByStudyIdResponse {

    @JsonProperty(value = "template_id")
    private Integer id;
    @JsonProperty(value = "study_type_id")
    private Integer studyTypeId;
    @JsonProperty(value = "template_name")
    private String templateName;
    @JsonProperty(value = "uploaded_by")
    private String uploadedBy;
    @JsonProperty(value = "created_date")
    private LocalDateTime createdDate;
    @JsonProperty(value = "updatedDate")
    private LocalDateTime updated_date;

    public static List<TemplateDetailsByStudyIdResponse> getTemplateDetailsByStudyIdResponse(StudyTypes studyTypes){
        return studyTypes.getTemplateDetails().stream()
                .map(value-> TemplateDetailsByStudyIdResponse.builder()
                            .id(value.getId())
                            .studyTypeId(studyTypes.getId())
                            .templateName(value.getTemplateName())
                            .uploadedBy(value.getUploadedBy())
                            .createdDate(value.getCreatedAt())
                            .updated_date(value.getUpdatedAt())
                            .build()).collect(Collectors.toList());
    }

}
