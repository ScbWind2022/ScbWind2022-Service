package com.example.rateservice.dto;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;

@Value
@Builder
public class DateCurrencyRateResponse {

    String date;

    String id;

    int nominal;

    BigDecimal value;

}
