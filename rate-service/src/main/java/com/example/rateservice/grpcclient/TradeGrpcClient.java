package com.example.rateservice.grpcclient;

import com.example.rateservice.dto.maindto.UserDTO;

public interface TradeGrpcClient {
    String openSession(UserDTO userDTO);
    String closeSession(UserDTO userDTO);
}
