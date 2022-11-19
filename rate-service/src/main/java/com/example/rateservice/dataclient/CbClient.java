package com.example.rateservice.dataclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@FeignClient
public interface CbClient {

    @GetMapping(value = "https://www.cbr.ru/scripts/XML_daily.asp")
    String getCurrencyRatesPlan();

    @GetMapping(value = "https://www.cbr.ru/scripts/XML_daily.asp")
    String getCurrencyRatesOnDate(@RequestParam("date_req") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate date);

    @GetMapping(value = "https://www.cbr.ru/scripts/XML_daily.asp")
    String getCurrencyRatesRange(@RequestParam("date_req1") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dateFrom,
                                 @RequestParam("date_req2") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dateTo,
                                 @RequestParam("VAL_NM_RQ") String currencyId);

    @GetMapping(value = "http://www.cbr.ru/scripts/XML_val.asp?d=0")
    String getCurrencyList();
}
