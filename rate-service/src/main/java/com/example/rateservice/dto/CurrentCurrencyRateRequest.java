package com.example.rateservice.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CurrentCurrencyRateRequest {

    String id;

}
