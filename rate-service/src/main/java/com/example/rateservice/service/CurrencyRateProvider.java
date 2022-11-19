package com.example.rateservice.service;

import com.example.rateservice.dto.CurrencyRateResponse;
import com.example.rateservice.dto.RangeCurrencyRateResponse;

import java.time.LocalDate;
import java.util.Map;

public interface CurrencyRateProvider {

    Map<String, CurrencyRateResponse> getCurrencyRates();

    RangeCurrencyRateResponse getCurrencyRatesRange(LocalDate dateFrom, LocalDate dateTo, String currencyId);

}
