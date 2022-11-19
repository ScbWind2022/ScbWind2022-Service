package com.example.authservice.service;

import com.example.authservice.dto.CurrencyRateResponse;
import com.example.authservice.dto.CurrencyResponse;
import com.example.authservice.dto.CurrentCurrencyRateRequest;

public interface RateService {
    CurrencyResponse[] getCurrencyList();
    CurrencyRateResponse getCurrencyRate(CurrentCurrencyRateRequest request);
}
