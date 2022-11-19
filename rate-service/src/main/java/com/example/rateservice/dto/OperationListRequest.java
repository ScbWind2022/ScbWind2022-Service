package com.example.rateservice.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class OperationListRequest {
    int accountId;
    String dateFrom;
    String dateTo;
}
