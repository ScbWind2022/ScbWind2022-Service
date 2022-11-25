package com.example.rateservice.grpcclient;

import com.example.rateservice.dto.domestic.AccountDto;
import com.example.rateservice.dto.domestic.UserDto;

public interface AccountGrpcClient {
    AccountDto[] getAccountsUser(UserDto userDTO);
    AccountDto changeSumInSession(AccountDto checkDto);
    String openSession(UserDto userDTO);
    String closeSession(UserDto userDTO);
}
