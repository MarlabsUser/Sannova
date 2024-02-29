package com.sannova.service;


import com.sannova.dto.FormConfirmationRequest;
import com.sannova.dto.IdGeneratorResponse;
import com.sannova.dto.IdGeneratorResquest;

import java.io.IOException;

public interface FormDetailsService {
    String getStudyNumber(Integer study_id);

    byte[] addFormConfirmationDetails(FormConfirmationRequest request) throws IOException;

    IdGeneratorResponse generateId(IdGeneratorResquest request);
}
