package com.example.authservice.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OperationListRequest {
    private int accountId;
    private String dateFrom;
    private String dateTo;
}
