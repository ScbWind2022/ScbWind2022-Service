package com.example.authservice.service.impl;

import com.example.authservice.dto.OperationListRequest;
import com.example.authservice.dto.TradeOperationRequest;
import com.example.authservice.dto.TradeOperationResponse;
import com.example.authservice.dto.TradeSessionRequest;
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

    @Override
    public TradeOperationResponse operationTrade(TradeOperationRequest request,String email) {
        return tradeGrpcClient.operateTrade(request,email);
    }

    @Override
    public TradeOperationResponse[] tradeOperationList(OperationListRequest request, String email) {
        return tradeGrpcClient.tradeOperationList(request,email);
    }

    @Override
    public String operateTradeSession(TradeSessionRequest request, String email) {
        return tradeGrpcClient.operateTradeSession(request,email);
    }
}
