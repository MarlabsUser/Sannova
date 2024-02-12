package com.sannova.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
public class SannovaExceptionHandler extends RuntimeException {

    private String message;
    private String error;
    private String url;
    private HttpStatus status;

}
