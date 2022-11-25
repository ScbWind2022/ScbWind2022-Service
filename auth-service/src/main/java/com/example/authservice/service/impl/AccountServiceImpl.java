package com.example.authservice.service.impl;

import com.example.authservice.dto.AccountEnableRequest;
import com.example.authservice.dto.AccountRequest;
import com.example.authservice.dto.AccountResponse;
import com.example.authservice.dto.CreateAccountRequest;
import com.example.authservice.dto.domestic.AccountDto;
import com.example.authservice.dto.domestic.UserDto;
import com.example.authservice.grpcclient.AccountGrpcClient;
import com.example.authservice.service.AccountService;
import com.example.authservice.utils.DtoUtils;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final Gson gson = new Gson();
    private final AccountGrpcClient checkGrpcClient;
    private final DtoUtils dtoUtils;

    @Override
    public AccountResponse[] getAccountByUserEmail(String email) {
        final UserDto req = UserDto.builder().email(email).build();
        final AccountDto[] dtos = checkGrpcClient.getAccountByEmail(req);
        if (dtos != null && dtos.length > 0) {
            final AccountResponse[] accountResponseDtos = new AccountResponse[dtos.length];
            int index = 0;
            for (AccountDto c : dtos) {
                accountResponseDtos[index] = dtoUtils.toAccountResponseDto(c);
                index++;
            }
            return accountResponseDtos;
        }
        return new AccountResponse[0];
    }

    @Override
    public AccountResponse changeSumByEmail(AccountRequest request, String email) {
        final AccountDto req = dtoUtils.toAccountDto(request);
        req.setUserEmail(email);
        final AccountDto accountDto = checkGrpcClient.changeSumByIdAndEmail(req);
        return dtoUtils.toAccountResponseDto(accountDto);
    }

    @Override
    public AccountResponse createAccount(CreateAccountRequest request, String email) {
        final AccountRequest accountRequestDto = AccountRequest.builder()
                .currencyId(request.getCurrencyId()).build();
        final AccountDto req = dtoUtils.toAccountDto(accountRequestDto);
        req.setUserEmail(email);
        final AccountDto accountDto = checkGrpcClient.createAccountByEmail(req);
        return dtoUtils.toAccountResponseDto(accountDto);
    }

    @Override
    public String changeEnabledByEmailAndId(AccountEnableRequest request, String email) {
        final AccountDto accountDto = dtoUtils.toAccountDto(request);
        accountDto.setUserEmail(email);
        return checkGrpcClient.changeEnableByIdAndEmail(accountDto);
    }
}
