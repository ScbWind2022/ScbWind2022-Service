package com.example.rateservice.grpcclient;

import com.example.rateservice.dto.maindto.CheckDto;
import com.example.rateservice.dto.maindto.UserDTO;

public interface AccountGrpcClient {
    CheckDto[] getAccountsUser(UserDTO userDTO);
    CheckDto changeSumInSession(CheckDto checkDto);
}
