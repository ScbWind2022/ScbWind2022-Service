package com.example.authservice.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CurrencyRateResponse {
    private String id;
    private String numCode;
    private String charCode;
    private int nominal;
    private String name;
    private double value;
}
