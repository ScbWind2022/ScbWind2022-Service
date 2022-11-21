package com.example.authservice.dto.domestic;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class TradeDto {
    private int id;
    private int userId;
    private String currencyCharCode;
    private String currencyId;
    private String currencyName;
    private String currencyEngName;
    private String sum;
    private String userEmail;
    private boolean enable;
    private int accountIdFrom;
    private int accountIdTo;
}
