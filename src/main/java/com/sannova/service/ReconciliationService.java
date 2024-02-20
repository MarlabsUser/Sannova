package com.sannova.service;

import com.sannova.dto.ReconciliationResponseDto;

import java.io.IOException;
import java.util.List;

public interface ReconciliationService {

    List<ReconciliationResponseDto> getReconsiliationDetails(String serialNumber,String FromDate,String ToDate);

    byte[] printReconsiliationDetails(List<ReconciliationResponseDto> reconciliationResponseDtos) throws IOException;


}
