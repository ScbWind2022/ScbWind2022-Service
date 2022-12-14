package com.example.authservice.controller;

import com.example.authservice.dto.CurrencyRateResponse;
import com.example.authservice.dto.CurrencyResponse;
import com.example.authservice.dto.CurrentCurrencyRateRequest;
import com.example.authservice.dto.RangeCurrencyRateRequest;
import com.example.authservice.dto.RangeCurrencyRateResponse;
import com.example.authservice.service.RateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/rate")
public class RateController {
    private final RateService rateService;

    @PostMapping(value = "/current")
    public ResponseEntity<CurrencyRateResponse> current(@RequestBody CurrentCurrencyRateRequest request,
                                                        Principal principal) {
        return new ResponseEntity<>(rateService.getCurrencyRate(request), HttpStatus.OK);
    }

    @PostMapping(value = "/range")
    public ResponseEntity<RangeCurrencyRateResponse> range(@RequestBody RangeCurrencyRateRequest request,
                                                           Principal principal) {
        return new ResponseEntity<>(rateService.getCurrencyInRange(request), HttpStatus.OK);
    }

    @PostMapping(value = "/current/list")
    public ResponseEntity<CurrencyResponse[]> getCurrencyList() {
        return new ResponseEntity<>(rateService.getCurrencyList(), HttpStatus.OK);
    }
}
