package com.example.authservice.grpcclient;

import com.example.authservice.dto.domestic.AccountDto;
import com.example.authservice.dto.domestic.UserDto;

public interface AccountGrpcClient {
    AccountDto[] getAccountByEmail(UserDto userDTO);

    AccountDto changeSumByIdAndEmail(AccountDto checkDto);

    String changeEnableByIdAndEmail(AccountDto checkDto);

    AccountDto createAccountByEmail(AccountDto checkDto);

}
