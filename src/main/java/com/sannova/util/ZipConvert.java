package com.sannova.util;

import com.sannova.dto.ZipFormattedFiles;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.Section;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xwpf.usermodel.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipConvert {
    public static byte[] zipBytes(List<ZipFormattedFiles> zipFormattedFiles) throws IOException {
        String newValeu="LPXXXX";
        String replaceName=" XXXX_REPLACE_XXX";

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try(ZipOutputStream zos = new ZipOutputStream(baos)) {
            for (ZipFormattedFiles file : zipFormattedFiles) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    XWPFDocument doc = new XWPFDocument(new ByteArrayInputStream(file.getFileByte()));
                    doc = replaceText(doc, replaceName, newValeu);
                    doc.write(byteArrayOutputStream);
                }catch (Exception exception){
                    exception.printStackTrace();
                }
                ZipEntry entry = new ZipEntry("modify.docx");
               // entry.setSize(changeFiles.length);
                zos.putNextEntry(entry);
                zos.write(byteArrayOutputStream.toByteArray());
            }
        }
        return baos.toByteArray();
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

    public static ZipFormattedFiles formattedZipArrayOfFiles(String fileName,byte[] bytes){
        return ZipFormattedFiles.builder()
                .fileName(fileName)
                .fileByte(bytes)
                .build();

    }
}
