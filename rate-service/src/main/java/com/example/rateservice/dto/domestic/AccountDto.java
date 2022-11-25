package com.example.rateservice.dto.domestic;

import lombok.*;

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
