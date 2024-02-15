package com.sannova.service;


import com.sannova.dto.FormConfirmationDetails;

public interface FormDetailsService {
    String getStudyNumber();

    String addFormConfirmationDetails(FormConfirmationDetails request);
}
