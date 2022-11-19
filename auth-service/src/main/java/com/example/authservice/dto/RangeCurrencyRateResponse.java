package com.example.authservice.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class RangeCurrencyRateResponse {
    LocalDate dateFrom;

    LocalDate dateTo;

    DateCurrencyRateResponse[] rates;
}
