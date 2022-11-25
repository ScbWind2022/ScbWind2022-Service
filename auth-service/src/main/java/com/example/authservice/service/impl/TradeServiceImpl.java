package com.example.authservice.service.impl;

import com.example.authservice.dto.OperationListRequest;
import com.example.authservice.dto.TradeOperationRequest;
import com.example.authservice.dto.TradeOperationResponse;
import com.example.authservice.dto.TradeSessionRequest;
import com.example.authservice.grpcclient.TradeGrpcClient;
import com.example.authservice.service.TradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TradeServiceImpl implements TradeService {
    private final TradeGrpcClient tradeGrpcClient;


    @Override
    public String operateTradeSession(TradeSessionRequest request, String email) {
        return tradeGrpcClient.operateTradeSession(request, email);
    }

    @Override
    public TradeOperationResponse operationTrade(TradeOperationRequest request, String email) {
        return tradeGrpcClient.operateTrade(request, email);
    }

    @Override
    public TradeOperationResponse[] tradeOperationList(OperationListRequest request, String email) {
        return tradeGrpcClient.tradeOperationList(request, email);
    }

}
