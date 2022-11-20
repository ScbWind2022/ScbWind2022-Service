package com.example.rateservice.service;

import com.example.rateservice.dto.AccountResponseDto;

public interface AccountService {
    AccountResponseDto[] getAccountsUserByEmail(String email);
    AccountResponseDto changeSumInAccountByUserEmailAndId(String email,int checkId,int sum);
}
