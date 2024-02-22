package com.sannova.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sannova.model.TemplateDetails;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;
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
    private String createdDate;
    @JsonProperty(value = "updatedDate")
    private String updated_date;

    public static List<TemplateDetailsByStudyIdResponse> getTemplateDetailsByStudyIdResponse(List<TemplateDetails> templateDetails){
        return templateDetails.stream()
                .map(value-> TemplateDetailsByStudyIdResponse.builder()
                            .id(value.getId())
                            .studyTypeId(value.getStudyTypes().getId())
                            .templateName(value.getTemplateName())
                            .uploadedBy(value.getUploadedBy())
                            .createdDate(value.getCreatedAt().format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")))
                            .updated_date(value.getUpdatedAt().format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")))
                            .build())
                .collect(Collectors.toList());
    }

}
