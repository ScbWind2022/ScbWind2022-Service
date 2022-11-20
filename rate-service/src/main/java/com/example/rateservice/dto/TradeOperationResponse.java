package com.example.rateservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
