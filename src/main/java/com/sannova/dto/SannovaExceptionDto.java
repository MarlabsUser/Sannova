package com.sannova.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
public class SannovaExceptionDto {
    private String message;
    private String error;
    private String url;
}
