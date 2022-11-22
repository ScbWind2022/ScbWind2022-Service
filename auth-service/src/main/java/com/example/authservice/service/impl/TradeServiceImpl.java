package com.example.authservice.service.impl;

import com.example.authservice.dto.OperationListRequest;
import com.example.authservice.dto.TradeOperationRequest;
import com.example.authservice.dto.TradeOperationResponse;
import com.example.authservice.dto.TradeSessionRequest;
import com.example.authservice.dto.domestic.UserDto;
import com.example.authservice.grpcclient.TradeGrpcClient;
import com.example.authservice.grpcclient.impl.TradeRateGrpcClientImpl;
import com.example.authservice.service.TradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TradeServiceImpl implements TradeService {
    private final TradeGrpcClient tradeGrpcClient;
    private final TradeRateGrpcClientImpl tradeRateGrpcClient;
    @Override
    public String openSession(String email) {
        final UserDto userDTO = UserDto.builder().email(email).build();
        return tradeGrpcClient.openSession(userDTO);
    }

    @Override
    public String closeSession(String email) {
        final UserDto userDTO = UserDto.builder().email(email).build();
        return tradeGrpcClient.closeSession(userDTO);
    }

    @Override
    public TradeOperationResponse operationTrade(TradeOperationRequest request,String email) {
        return tradeRateGrpcClient.operateTrade(request,email);
    }

    @Override
    public TradeOperationResponse[] tradeOperationList(OperationListRequest request, String email) {
        return tradeRateGrpcClient.tradeOperationList(request,email);
    }

    @Override
    public String operateTradeSession(TradeSessionRequest request, String email) {
        return tradeRateGrpcClient.operateTradeSession(request,email);
    }
}
