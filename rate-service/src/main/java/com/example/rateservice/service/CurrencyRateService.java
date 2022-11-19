package com.example.rateservice.service;

import com.example.rateservice.dto.CurrencyRateRequest;
import com.example.rateservice.dto.CurrencyRateResponse;
import com.example.rateservice.dto.CurrencyResponse;
import com.example.rateservice.dto.RangeCurrencyRateRequest;
import com.example.rateservice.dto.RangeCurrencyRateResponse;

import java.util.List;

public interface CurrencyRateService {

    CurrencyRateResponse getCurrentCurrencyRate(CurrencyRateRequest request);

    RangeCurrencyRateResponse getRangeCurrencyRate(RangeCurrencyRateRequest request);

    List<CurrencyResponse> getCurrencyList();

}
