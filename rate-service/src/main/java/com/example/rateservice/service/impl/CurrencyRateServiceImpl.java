package com.example.rateservice.service.impl;

import com.example.rateservice.dto.CurrencyRateRequest;
import com.example.rateservice.dto.CurrencyRateResponse;
import com.example.rateservice.dto.CurrencyResponse;
import com.example.rateservice.dto.RangeCurrencyRateRequest;
import com.example.rateservice.dto.RangeCurrencyRateResponse;
import com.example.rateservice.service.CurrencyRateProvider;
import com.example.rateservice.service.CurrencyRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CurrencyRateServiceImpl implements CurrencyRateService {

    private final CurrencyRateProvider currencyRateProvider;

    @Override
    public CurrencyRateResponse getCurrentCurrencyRate(CurrencyRateRequest request) {
        return currencyRateProvider.getCurrencyRates().get(request.getId());
    }

    @Override
    public RangeCurrencyRateResponse getRangeCurrencyRate(RangeCurrencyRateRequest request) {
        return currencyRateProvider.getCurrencyRatesRange(request.getDateFrom(),
                request.getDateTo(),
                request.getId());
    }

    @Override
    public List<CurrencyResponse> getCurrencyList() {
        var values = currencyRateProvider.getCurrencyRates().values();
        if (values == null || values.isEmpty()) {
            return List.of();
        }

        return values.stream()
                .map(e -> CurrencyResponse.builder()
                        .id(e.getId())
                        .numCode(e.getNumCode())
                        .charCode(e.getCharCode())
                        .nominal(e.getNominal())
                        .name(e.getName())
                        .build())
                .collect(Collectors.toList());
    }

}
