package com.example.authservice.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountResponseDto {
    private int id;
    private int userId;
    private String currencyId;
    private String currencyName;
    private String currencyEngName;
    private String currencyCharCode;
    private String sum;
    private boolean enabled;
}
