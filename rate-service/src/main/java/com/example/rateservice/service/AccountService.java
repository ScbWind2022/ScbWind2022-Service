package com.example.rateservice.service;

import com.example.rateservice.dto.AccountResponseDto;

import java.math.BigDecimal;

public interface AccountService {
    AccountResponseDto[] getAccountsUserByEmail(String email);

    AccountResponseDto changeSumInAccountByUserEmailAndId(String email, int checkId, BigDecimal sum);
}
