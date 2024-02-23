package com.sannova.dto;

import com.sannova.model.FormPrintDetails;
import com.sannova.model.FormPrintDetailsNew;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReconciliationResponseDto {
    private String studyNumber;
    private String formTitle;
    private Integer formPrinted;
    private String printedBy;
    private String dateAndTime;
    private String studyType;
    private Integer templateId;

    public static ReconciliationResponseDto build(FormPrintDetails formPrintDetails){
        return ReconciliationResponseDto.builder()
                .studyNumber(formPrintDetails.getStudyName())
                .formTitle(formPrintDetails.getTemplateDetails().getTemplateName())
                .formPrinted(formPrintDetails.getNumberOfFormsCount())
                .printedBy((formPrintDetails.getPrintBy()))
                .dateAndTime(formPrintDetails.getCreatedAt().format(DateTimeFormatter.ofPattern("dd MMM yyyy")))
                .studyType(formPrintDetails.getStudyId().getStudyName())
                .templateId(formPrintDetails.getTemplateDetails().getId())
                .build();
    }

    public static ReconciliationResponseDto build(FormPrintDetailsNew formPrintDetails){
        return ReconciliationResponseDto.builder()
                .studyNumber(formPrintDetails.getStudyName())
                .formTitle(formPrintDetails.getTemplateDetails().getTemplateName())
                .formPrinted(formPrintDetails.getNumberOfFormsCount())
                .printedBy((formPrintDetails.getPrintBy()))
                .dateAndTime(formPrintDetails.getCreatedAt().format(DateTimeFormatter.ofPattern("dd MMM yyyy")))
                .studyType(formPrintDetails.getStudyId().getStudyName())
                .templateId(formPrintDetails.getTemplateDetails().getId())
                .build();
    }
}
