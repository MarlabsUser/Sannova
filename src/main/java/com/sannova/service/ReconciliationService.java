package com.sannova.service;

import com.sannova.dto.ReconciliationResponseDto;

public interface ReconciliationService {

    ReconciliationResponseDto getReconsiliationDetails(String serialNumber,String FromDate, String ToDate);

}
