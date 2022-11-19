package com.example.rateservice.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CurrencyResponse {

    String id;

    String numCode;

    String charCode;

    int nominal;

    String name;

}
