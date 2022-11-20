package com.example.authservice.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TradeOperationResponse {
    private int accountIdFrom;
    private int accountIdTo;
    private int id;
    private String date;
    private int accountId;
    private String currencyId;
    private String operation;
    private BigDecimal currencyRate;
    private BigDecimal amount;
    private BigDecimal sum;
}
