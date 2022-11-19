package com.example.rateservice.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class TradeOperationResponse {
    int id;
    String date;
    int accountId;
    String currencyId;
    String operation;
    double currencyRate;
    int amount;
    int sum;
}
