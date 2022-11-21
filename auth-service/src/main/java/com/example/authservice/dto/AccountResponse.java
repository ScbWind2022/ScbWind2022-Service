package com.example.authservice.dto;

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
public class AccountResponse {
    private int id;
    private int userId;
    private String currencyId;
    private String currencyName;
    private String currencyCharCode;
    private String sum;
    private boolean enabled;
}
