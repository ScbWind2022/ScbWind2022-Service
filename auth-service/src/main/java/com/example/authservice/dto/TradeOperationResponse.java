package com.example.authservice.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TradeOperationResponse {
    private int id;
    private String date;
    private int accountId;
    private String currencyId;
    private String operation;
    private double currencyRate;
    private int amount;
    private int sum;
}
