package com.sannova.service;

import com.sannova.dto.ReconciliationResponseDto;

import java.util.List;

public interface ReconciliationService {

    List<ReconciliationResponseDto> getReconsiliationDetails(String serialNumber,String FromDate,String ToDate);

}
