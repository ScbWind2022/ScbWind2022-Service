package com.example.authservice.grpcClient;

import com.example.authservice.dto.maindto.UserDTO;

public interface TradeGrpcClient {
    String openSession(UserDTO userDTO);
    String closeSession(UserDTO userDTO);
}
