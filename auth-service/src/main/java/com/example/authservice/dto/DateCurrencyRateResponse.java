package com.example.authservice.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Builder
public class DateCurrencyRateResponse {String date;

    String id;

    int nominal;

    BigDecimal value;

}
