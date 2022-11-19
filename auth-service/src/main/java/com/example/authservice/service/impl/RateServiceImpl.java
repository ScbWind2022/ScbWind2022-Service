package com.example.authservice.service.impl;

import com.example.authservice.dto.CurrencyResponse;
import com.example.authservice.grpcClient.RateGrpcClient;
import com.example.authservice.service.RateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RateServiceImpl implements RateService {
    private final RateGrpcClient rateGrpcClient;
    @Override
    public CurrencyResponse[] getCurrencyList() {
        final CurrencyResponse[] currencyResponses = rateGrpcClient.getCurrencyList();
        return currencyResponses;
    }
}
