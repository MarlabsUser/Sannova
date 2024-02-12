package com.sannova.exceptionhandler;

import com.sannova.dto.SannovaExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@ControllerAdvice
public class SannovaCentralException {

    @ExceptionHandler(SannovaExceptionHandler.class)
    @ResponseBody
    public ResponseEntity<SannovaExceptionDto> handleMultipartException(SannovaExceptionHandler ex,HttpServletRequest request) {
        return  new ResponseEntity<>(SannovaExceptionDto.builder()
                .message(ex.getMessage()).url(request.getRequestURI()).error(ex.getError()).build(),ex.getStatus());
    }
}
