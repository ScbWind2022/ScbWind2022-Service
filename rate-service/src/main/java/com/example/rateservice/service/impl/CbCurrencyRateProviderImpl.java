package com.example.rateservice.service.impl;

import com.example.rateservice.dataclient.CbClient;
import com.example.rateservice.dto.CurrencyRateResponse;
import com.example.rateservice.dto.RangeCurrencyRateResponse;
import com.example.rateservice.parser.CurrencyRateParser;
import com.example.rateservice.service.CurrencyRateProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CbCurrencyRateProviderImpl implements CurrencyRateProvider {

    private final CbClient cbClient;

    private final CurrencyRateParser parser;

    @Override
    @Cacheable(value = "rates")
    public Map<String, CurrencyRateResponse> getCurrencyRates() {
        final LocalDate date = LocalDate.now();
        String xml = cbClient.getCurrencyRatesOnDate(date);
        return parser.parse(xml)
                .stream()
                .collect(Collectors.toMap(CurrencyRateResponse::getCharCode, item -> item));
    }

    @Override
    public RangeCurrencyRateResponse getCurrencyRatesRange(LocalDate dateFrom, LocalDate dateTo, String currencyId) {
        String xml = cbClient.getCurrencyRatesRange(dateFrom, dateTo, currencyId);
        return RangeCurrencyRateResponse.builder()
                .dateFrom(dateFrom)
                .dateTo(dateTo)
                .rates(parser.parseRange(xml))
                .build();
    }

}
