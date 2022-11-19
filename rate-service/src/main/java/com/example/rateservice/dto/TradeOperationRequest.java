package com.example.rateservice.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class TradeOperationRequest {
    private String currencyId;
    private String operation;
    private double currencyRate;
    private int amount;
    private double sum;
}
