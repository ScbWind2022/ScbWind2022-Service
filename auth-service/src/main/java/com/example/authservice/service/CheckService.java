package com.example.authservice.service;

import com.example.authservice.dto.AccountRequestDto;
import com.example.authservice.dto.AccountResponseDto;
import com.example.authservice.dto.CreateAccountRequestDto;
import com.example.authservice.dto.UserEnableRequest;

public interface CheckService {
    AccountResponseDto[] getCheckWithUserByEmail(String email);
    AccountResponseDto changeSumByEmail(AccountRequestDto accountRequestDto,String email);
    AccountResponseDto createCheckByEmail(CreateAccountRequestDto createAccountRequestDto, String email);
    String changeEnabledByEmailAndId(UserEnableRequest userEnableRequest,String email);
}
