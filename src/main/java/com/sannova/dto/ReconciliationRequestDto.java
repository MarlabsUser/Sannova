package com.sannova.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReconciliationRequestDto {

    @JsonProperty("firstname")
    private String search;
    @JsonProperty("from_date")
    private String fromDate;
    @JsonProperty("to-date")
    private String toDate;
}
