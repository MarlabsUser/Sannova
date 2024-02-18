package com.sannova.service;

import com.sannova.dto.ReconciliationResponseDto;
import com.sannova.model.FormPrintDetails;
import com.sannova.repository.FormPrintRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReconsiliationServiceImpl implements ReconciliationService{
    private final FormPrintRepository formPrintRepository;
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
                //will see
            }
        }else if(FromDate!=null && ToDate != null){
            LocalDateTime requestFromDate=LocalDate.parse(FromDate,DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).atStartOfDay();
            LocalDateTime requestToDate=LocalDate.parse(ToDate,DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).atStartOfDay();

            if(requestFromDate.getYear()==LocalDate.now().getYear()){
                List<FormPrintDetails> formPrintDetails=formPrintRepository.findByCreatedAtGreaterThanEqualAndCreatedAtLessThanEqual(requestFromDate,requestToDate);
                List<ReconciliationResponseDto> reconciliationResponseDtos=formPrintDetails.stream().map(ReconciliationResponseDto::build).collect(Collectors.toList());
                return reconciliationResponseDtos;
            }
        }
        return new ArrayList<>();
    }
}
