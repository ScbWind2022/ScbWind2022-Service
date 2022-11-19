package com.example.rateservice.parser;

import com.example.rateservice.dto.CurrencyRateResponse;
import com.example.rateservice.dto.DateCurrencyRateResponse;

import java.util.List;

public interface CurrencyRateParser {

    List<CurrencyRateResponse> parse(String ratesAsString);

    List<DateCurrencyRateResponse> parseRange(String rangeAsString);

}
