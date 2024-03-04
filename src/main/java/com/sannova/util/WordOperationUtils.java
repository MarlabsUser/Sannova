package com.sannova.util;

import com.sannova.dto.ZipFormattedFiles;
import com.sannova.model.SerialNumberCount;
import com.sannova.model.TemplateDetails;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class WordOperationUtils {

    final static String replacementName="XXXX_REPLACE_XXX";

    //ZipFormattedFiles
    public static List<ZipFormattedFiles> replaceDocxBySerialId(List<SerialNumberCount> serialNumberCount){
        List<ZipFormattedFiles> zipFormatted=serialNumberCount.stream().map(serialDetails->{
            ZipFormattedFiles zipFormattedFiles=new ZipFormattedFiles();
            try{
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                TemplateDetails templateDetails = serialDetails.getFormPrintDetails().getTemplateDetails();
                XWPFDocument doc = new XWPFDocument(new ByteArrayInputStream(templateDetails.getData()));
                doc = replaceText(doc, replacementName, serialDetails.getSerialNumber());
                doc.write(outputStream);
                zipFormattedFiles.setFileByte(outputStream.toByteArray());
                if(templateDetails.getTemplateName().substring(templateDetails.getTemplateName().lastIndexOf(".") + 1).equals("docx")){
                    zipFormattedFiles.setFileName(serialDetails.getFormPrintDetails().getTemplateDetails().getTemplateName()+"/"+serialDetails.getSerialNumber()+".docx");
                }else{
                    zipFormattedFiles.setFileName(serialDetails.getFormPrintDetails().getTemplateDetails().getTemplateName()+"/"+serialDetails.getSerialNumber()+".pdf");
                }
                return zipFormattedFiles;
            }catch (Exception e){
                e.printStackTrace();
                return zipFormattedFiles;
            }})
                .filter(v-> StringUtils.isNotBlank(v.getFileName()))
                .collect(Collectors.toList());
        return zipFormatted;
    }


    private static XWPFDocument replaceText(XWPFDocument doc,String originalText,String updatedText) {
        replaceTextInParagraphs(doc.getParagraphs(), originalText, updatedText);

        for (XWPFTable tbl : doc.getTables()) {
            for (XWPFTableRow row : tbl.getRows()) {
                for (XWPFTableCell cell : row.getTableCells()) {
                    replaceTextInParagraphs(cell.getParagraphs(), originalText, updatedText);
                }
            }
        }
        return doc;
    }

    private static void replaceTextInParagraphs(List<XWPFParagraph> paragraphs,String originalText,String updatedText) {
        paragraphs.forEach(paragraph -> replaceTextInParagraph(paragraph, originalText, updatedText));
    }

    private static void replaceTextInParagraph(XWPFParagraph paragraph,String originalText,String updatedText) {
        String paragraphText = paragraph.getParagraphText();
        if (paragraphText.contains(originalText)) {
            String updatedParagraphText = paragraphText.replace(originalText, updatedText);
            while (paragraph.getRuns().size() > 0) {
                paragraph.removeRun(0);
            }
            XWPFRun newRun = paragraph.createRun();
            newRun.setText(updatedParagraphText);
        }
    }
}
