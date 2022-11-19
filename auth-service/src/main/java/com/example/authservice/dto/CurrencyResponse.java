package com.example.authservice.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class CurrencyResponse {
    String id;

    String numCode;

    String charCode;

    int nominal;

    String name;
}
