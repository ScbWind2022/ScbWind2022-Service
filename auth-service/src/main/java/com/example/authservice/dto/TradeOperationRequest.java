package com.example.authservice.dto;

import lombok.*;

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
