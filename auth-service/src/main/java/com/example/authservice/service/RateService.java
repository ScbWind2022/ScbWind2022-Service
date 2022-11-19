package com.example.authservice.service;

import com.example.authservice.dto.CurrencyResponse;

public interface RateService {
    CurrencyResponse[] getCurrencyList();
}
