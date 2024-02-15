package com.sannova.service;


import com.sannova.dto.FormConfirmationRequest;

import java.io.IOException;

public interface FormDetailsService {
    String getStudyNumber();

    String addFormConfirmationDetails(FormConfirmationRequest request) throws IOException;
}
