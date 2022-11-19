package com.example.rateservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RangeCurrencyRateResponse {

    private LocalDate dateFrom;

    private LocalDate dateTo;

    private List<DateCurrencyRateResponse> rates;
}
