package com.example.authservice.grpcclient;

import com.example.authservice.dto.*;

public interface RateGrpcClient {
    CurrencyRateResponse getCurrentCurrencyRate(CurrentCurrencyRateRequest request);
    RangeCurrencyRateResponse getRangeCurrencyRate(RangeCurrencyRateRequest request);

    CurrencyResponse[] getCurrencyList();
}
