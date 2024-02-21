package com.sannova.service;

import com.sannova.dto.ReconciliationResponseDto;
import com.sannova.dto.ZipFormattedFiles;
import com.sannova.model.FormPrintDetails;
import com.sannova.model.FormPrintDetailsNew;
import com.sannova.repository.FormPrintNewRepository;
import com.sannova.repository.FormPrintRepository;
import com.sannova.repository.TemplateDetailsRepository;
import com.sannova.util.ZipConvert;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReconsiliationServiceImpl implements ReconciliationService{
    private final FormPrintRepository formPrintRepository;
    private final TemplateDetailsRepository templateDetailsRepository;

    private final FormPrintNewRepository formPrintNewRepository;
    @Override
    public List<ReconciliationResponseDto> getReconsiliationDetails(String serialNumber,
                                                                    String FromDate,
                                                                    String ToDate) {
        if(StringUtils.isNotBlank(serialNumber)){
            if(Integer.parseInt(serialNumber.substring(serialNumber.length()-4))== LocalDate.now().getYear()){
                List<FormPrintDetails> formPrintDetails= formPrintRepository.findByStudyName(serialNumber);
                List<ReconciliationResponseDto> reconciliationResponseDtos=formPrintDetails.stream().map(ReconciliationResponseDto::build).collect(Collectors.toList());
                return reconciliationResponseDtos;
            }else{
                List<FormPrintDetailsNew> formPrintDetails= formPrintNewRepository.findByStudyName(serialNumber);
                List<ReconciliationResponseDto> reconciliationResponseDtos=formPrintDetails.stream().map(ReconciliationResponseDto::build).collect(Collectors.toList());
                return reconciliationResponseDtos;

            }
        }else if(FromDate!=null && ToDate != null){
            LocalDateTime requestFromDate=LocalDate.parse(FromDate,DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).atStartOfDay();
            LocalDateTime requestToDate=LocalDate.parse(ToDate,DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).atStartOfDay();

            if(requestFromDate.getYear()==LocalDate.now().getYear()){
                List<FormPrintDetails> formPrintDetails=formPrintRepository.findByCreatedAtGreaterThanEqualAndCreatedAtLessThanEqual(requestFromDate,requestToDate);
                List<ReconciliationResponseDto> reconciliationResponseDtos=formPrintDetails.stream().map(ReconciliationResponseDto::build).collect(Collectors.toList());
                return reconciliationResponseDtos;
            }else{
                List<FormPrintDetailsNew> formPrintDetails=formPrintNewRepository.findByCreatedAtGreaterThanEqualAndCreatedAtLessThanEqual(requestFromDate,requestToDate);
                List<ReconciliationResponseDto> reconciliationResponseDtos=formPrintDetails.stream().map(ReconciliationResponseDto::build).collect(Collectors.toList());
                return reconciliationResponseDtos;
            }
        }
        return new ArrayList<>();
    }

    @Override
    public byte[] printReconsiliationDetails(List<ReconciliationResponseDto> reconciliationResponseDtos) throws IOException {
        List<ZipFormattedFiles> zipFormattedFiles=reconciliationResponseDtos.stream().map(v-> ZipConvert.formattedZipArrayOfFiles(v.getStudyNumber()+"/"+v.getFormTitle(),
                        templateDetailsRepository.findById(v.getTemplateId()).get().getData()))
                .collect(Collectors.toList());

        return ZipConvert.zipBytes(zipFormattedFiles);
        }

    @Override
    public byte[] getReconciliationDetailsDownload(List<ReconciliationResponseDto> request) throws IOException {

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("ReconciliationDetails");
        //workbook bold style
        CellStyle wbStyleBold = workbook.createCellStyle();
        Font wbFont = workbook.createFont();
        wbFont.setBold(true);
        wbStyleBold.setFont(wbFont);
        wbStyleBold.setAlignment(HorizontalAlignment.CENTER);

        CellStyle wrapTextStyle = workbook.createCellStyle();
        wrapTextStyle.setWrapText(true);
        wrapTextStyle.setAlignment(HorizontalAlignment.CENTER);
        //Wrapping text

        AtomicInteger rows = new AtomicInteger(0);

        Row row = sheet.createRow(rows.getAndIncrement());
        Cell c0 = row.createCell(0);
        c0.setCellStyle(wbStyleBold);
        c0.setCellValue("Study name");
        Cell c1 = row.createCell(1);
        c1.setCellStyle(wbStyleBold);
        c1.setCellValue("Form Title");
        Cell c2 = row.createCell(2);
        c2.setCellStyle(wbStyleBold);
        c2.setCellValue("Number of form printed");
        Cell c3 = row.createCell(3);
        c3.setCellStyle(wbStyleBold);
        c3.setCellValue("Printed by");
        Cell c4 = row.createCell(4);
        c4.setCellStyle(wbStyleBold);
        c4.setCellValue("Study number");
        Cell c5 = row.createCell(5);
        c5.setCellStyle(wbStyleBold);
        c5.setCellValue("Date");

        ByteArrayOutputStream fo = new ByteArrayOutputStream();
        for (ReconciliationResponseDto columnsDetails : request) {
            Row row1 = sheet.createRow(rows.getAndIncrement());
            Cell cv0 = row1.createCell(0);
            cv0.setCellStyle(wrapTextStyle);
            cv0.setCellValue(columnsDetails.getStudyType());
            Cell cv1 = row1.createCell(1);
            cv1.setCellStyle(wrapTextStyle);
            cv1.setCellValue(columnsDetails.getFormTitle());
            Cell cv2 = row1.createCell(2);
            cv2.setCellValue(columnsDetails.getFormPrinted());
            Cell cv3 = row1.createCell(3);
            cv3.setCellStyle(wrapTextStyle);
            cv3.setCellValue(columnsDetails.getPrintedBy());
            Cell cv4 = row1.createCell(4);
            cv4.setCellStyle(wrapTextStyle);
            cv4.setCellValue(columnsDetails.getStudyNumber());
            Cell cv5 = row1.createCell(5);
            cv5.setCellStyle(wrapTextStyle);
            cv5.setCellValue(columnsDetails.getDateAndTime());
        }

        sheet.getColumnWidthInPixels(10);
        workbook.write(fo);

        fo.flush();
        fo.close();
        return fo.toByteArray();
    }
}
