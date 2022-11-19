package com.example.rateservice.dataclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@FeignClient(name = "dailyrates",
        url = "${cb.url.daily}")
public interface CbClientDaily {

    @GetMapping
    String getCurrencyRatesOnDate(@RequestParam("date_req") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate date);


}
