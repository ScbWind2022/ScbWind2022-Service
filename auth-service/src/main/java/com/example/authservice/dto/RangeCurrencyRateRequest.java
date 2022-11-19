package com.example.authservice.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class RangeCurrencyRateRequest {
    String id;

    LocalDate dateFrom;

    LocalDate dateTo;
}
