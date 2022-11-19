package com.example.rateservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TradeOperationRequest {
    private String currencyId;
    private String operation;
    private double currencyRate;
    private int amount;
    private double sum;
}
