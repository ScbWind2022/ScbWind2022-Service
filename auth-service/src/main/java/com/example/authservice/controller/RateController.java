package com.example.authservice.controller;

import com.example.authservice.dto.CurrencyRateResponse;
import com.example.authservice.dto.CurrentCurrencyRateRequest;
import lombok.RequiredArgsConstructor;
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
    @PostMapping(value = "/current")
    public ResponseEntity<CurrencyRateResponse> current(@RequestBody CurrentCurrencyRateRequest request,
                                                        Principal principal){
        return null;
    }
    @PostMapping(value = "/range")
    public ResponseEntity<CurrencyRateResponse> range(@RequestBody CurrentCurrencyRateRequest request,
                                                      Principal principal){
        return null;
    }
}
