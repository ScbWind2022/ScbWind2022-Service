package com.example.rateservice.dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;
import java.util.List;

@Value
@Builder
public class RangeCurrencyRateResponse {

    LocalDate dateFrom;

    LocalDate dateTo;

    List<DateCurrencyRateResponse> rates;
}
