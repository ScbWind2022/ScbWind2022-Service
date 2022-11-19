package com.example.authservice.dto.maindto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CheckDto {
    private int id;
    private int userId;
    private String currencyCharCode;
    private String currencyId;
    private String currencyName;
    private String currencyEngName;
    private String sum;
    private String userEmail;
    private boolean enable;
}
