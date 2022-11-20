package com.example.rateservice.dto;

import lombok.*;

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
