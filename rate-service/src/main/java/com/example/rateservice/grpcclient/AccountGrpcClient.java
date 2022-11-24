package com.example.rateservice.grpcclient;

import com.example.rateservice.dto.maindto.AccountDto;
import com.example.rateservice.dto.maindto.UserDTO;

public interface AccountGrpcClient {
    AccountDto[] getAccountsUser(UserDTO userDTO);
    AccountDto changeSumInSession(AccountDto checkDto);
    String openSession(UserDTO userDTO);
    String closeSession(UserDTO userDTO);
}
