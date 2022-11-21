package com.example.authservice.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountRequest {
    private int id;
    private int userId;
    private String currencyCharCode;
    private String currencyId;
    private String sum;
}
