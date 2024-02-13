package com.sannova.service;

import com.sannova.dto.ReconciliationResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReconsiliationServiceImpl implements ReconciliationService{

    @Override
    public ReconciliationResponseDto getReconsiliationDetails(String serialNumber,String FromDate,String ToDate) {
        return null;
    }
}
