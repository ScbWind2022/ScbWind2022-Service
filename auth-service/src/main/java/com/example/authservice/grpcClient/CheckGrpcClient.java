package com.example.authservice.grpcClient;

import com.example.authservice.dto.domestic.AccountDto;
import com.example.authservice.dto.domestic.UserDto;

public interface CheckGrpcClient {
    AccountDto[] getCheckByEmail(UserDto userDTO);
    AccountDto changeSumByIdAndEmail(AccountDto checkDto);
    String changeEnableByIdAndEmail(AccountDto checkDto);
    AccountDto createCheckByEmail(AccountDto checkDto);
}
