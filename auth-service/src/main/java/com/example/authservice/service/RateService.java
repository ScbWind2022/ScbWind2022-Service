package com.example.authservice.service;

import com.example.authservice.dto.*;

public interface RateService {
    CurrencyResponse[] getCurrencyList();
    CurrencyRateResponse getCurrencyRate(CurrentCurrencyRateRequest request);
    RangeCurrencyRateResponse getCurrencyInRange(RangeCurrencyRateRequest request);
}
