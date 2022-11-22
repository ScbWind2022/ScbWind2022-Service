package com.example.authservice.service.impl;

import com.example.authservice.dto.*;
import com.example.authservice.exception.NotValidRequestException;
import com.example.authservice.grpcclient.RateGrpcClient;
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

    @Override
    public CurrencyRateResponse getCurrencyRate(CurrentCurrencyRateRequest request) {
        if(request == null){
            throw new NotValidRequestException();
        }
        if(request.getId() == null){
            throw new NotValidRequestException();
        }
        return rateGrpcClient.getCurrentCurrencyRate(request);
    }

    @Override
    public RangeCurrencyRateResponse getCurrencyInRange(RangeCurrencyRateRequest request) {
        RangeCurrencyRateResponse response = rateGrpcClient.getRangeCurrencyRate(request);
        return response;
    }
}
