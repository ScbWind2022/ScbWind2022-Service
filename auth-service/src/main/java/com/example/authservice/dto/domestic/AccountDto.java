package com.example.authservice.dto.domestic;

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
    private int userId;
    private String currencyCharCode;
    private String currencyId;
    private String currencyName;
    private String sum;
    private String userEmail;
    private boolean enable;
}
