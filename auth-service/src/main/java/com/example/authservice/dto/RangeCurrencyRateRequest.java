package com.example.authservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class RangeCurrencyRateRequest {
    private String id;

    private String dateFrom;

    private String dateTo;
    private DateCurrencyRateResponse[] rates;
}
