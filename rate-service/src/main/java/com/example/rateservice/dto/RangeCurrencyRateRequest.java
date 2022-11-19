package com.example.rateservice.dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class RangeCurrencyRateRequest {

    String id;

    LocalDate dateFrom;

    LocalDate dateTo;

}
