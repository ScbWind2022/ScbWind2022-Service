package com.example.authservice.service.impl;

import com.example.authservice.dto.maindto.UserDTO;
import com.example.authservice.grpcClient.TradeGrpcClient;
import com.example.authservice.service.TradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TradeServiceImpl implements TradeService {
    private final TradeGrpcClient tradeGrpcClient;
    @Override
    public String openSession(String email) {
        final UserDTO userDTO = UserDTO.builder().email(email).build();
        return tradeGrpcClient.openSession(userDTO);
    }

    @Override
    public String closeSession(String email) {
        final UserDTO userDTO = UserDTO.builder().email(email).build();
        return tradeGrpcClient.closeSession(userDTO);
    }
}
