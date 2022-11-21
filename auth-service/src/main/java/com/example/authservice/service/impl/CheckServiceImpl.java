package com.example.authservice.service.impl;

import com.example.authservice.dto.AccountEnableRequest;
import com.example.authservice.dto.AccountRequest;
import com.example.authservice.dto.AccountResponse;
import com.example.authservice.dto.CreateAccountRequest;
import com.example.authservice.dto.domestic.AccountDto;
import com.example.authservice.dto.domestic.UserDto;
import com.example.authservice.grpcClient.CheckGrpcClient;
import com.example.authservice.service.CheckService;
import com.example.authservice.utils.DtoUtils;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CheckServiceImpl implements CheckService {
    private final Gson gson = new Gson();
    private final CheckGrpcClient checkGrpcClient;
    private final DtoUtils dtoUtils;

    @Override
    public AccountResponse[] getAccountByUserEmail(String email) {
        final UserDto req = UserDto.builder().email(email).build();
        final AccountDto[] checkDtos = checkGrpcClient.getCheckByEmail(req);
        if (checkDtos != null && checkDtos.length > 0) {
            final AccountResponse[] accountResponseDtos = new AccountResponse[checkDtos.length];
            int index = 0;
            for (AccountDto c : checkDtos) {
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
        final AccountDto checkDto = checkGrpcClient.changeSumByIdAndEmail(req);
        return dtoUtils.toAccountResponseDto(checkDto);
    }

    @Override
    public AccountResponse createAccount(CreateAccountRequest request, String email) {
        final AccountRequest accountRequestDto = AccountRequest.builder()
                .currencyId(request.getCurrencyId()).build();
        final AccountDto req = dtoUtils.toAccountDto(accountRequestDto);
        req.setUserEmail(email);
        final AccountDto checkDto = checkGrpcClient.createCheckByEmail(req);
        return dtoUtils.toAccountResponseDto(checkDto);
    }

    @Override
    public String changeEnabledByEmailAndId(AccountEnableRequest request, String email) {
        final AccountDto checkDto = dtoUtils.toAccountDto(request);
        checkDto.setUserEmail(email);
        return checkGrpcClient.changeEnableByIdAndEmail(checkDto);
    }
}
