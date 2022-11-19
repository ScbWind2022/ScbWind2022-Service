package com.example.authservice.grpcClient;

import com.example.authservice.dto.*;

import java.util.List;

public interface RateGrpcClient {
    CurrencyRateResponse getCurrentCurrencyRate(CurrentCurrencyRateRequest request);
    RangeCurrencyRateResponse getRangeCurrencyRate(RangeCurrencyRateRequest request);

    CurrencyResponse[] getCurrencyList();
}
