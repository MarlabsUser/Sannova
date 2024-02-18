package com.sannova.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReconciliationRequestDto {

    @JsonProperty("serial_number")
    private String serialNumber;
    @JsonProperty("from_date")
    private String fromDate;
    @JsonProperty("to-date")
    private String toDate;
}
