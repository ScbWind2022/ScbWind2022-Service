package com.example.authservice.service;

import com.example.authservice.dto.AccountEnableRequest;
import com.example.authservice.dto.AccountRequest;
import com.example.authservice.dto.AccountResponse;
import com.example.authservice.dto.CreateAccountRequest;

public interface CheckService {
    AccountResponse[] getAccountByUserEmail(String email);

    AccountResponse changeSumByEmail(AccountRequest request, String email);

    AccountResponse createAccount(CreateAccountRequest request, String email);

    String changeEnabledByEmailAndId(AccountEnableRequest request, String email);
}
