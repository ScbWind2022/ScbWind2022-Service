package com.example.authservice.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountRequestDto {
    private int id;
    private int userId;
    private String currencyCharCode;
    private String sum;
}
