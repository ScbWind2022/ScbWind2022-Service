package com.example.userservice.dto;

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
public class AccountDto {
    private int id;
    private long userId;
    private UserDto owner;
    private String currencyCharCode;
    private String currencyId;
    private String currencyName;
    private String currencyEngName;
    private String sum;
    private String userEmail;
    private boolean enable;
}
