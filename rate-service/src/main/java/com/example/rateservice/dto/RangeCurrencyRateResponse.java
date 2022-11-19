package com.example.rateservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RangeCurrencyRateResponse {

    private String dateFrom;

    private String dateTo;

    private List<DateCurrencyRateResponse> rates;
}
