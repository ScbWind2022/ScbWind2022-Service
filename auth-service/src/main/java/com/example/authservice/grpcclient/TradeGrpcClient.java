package com.example.authservice.grpcclient;

import com.example.authservice.dto.OperationListRequest;
import com.example.authservice.dto.TradeOperationRequest;
import com.example.authservice.dto.TradeOperationResponse;
import com.example.authservice.dto.TradeSessionRequest;

public interface TradeGrpcClient {
    String operateTradeSession(TradeSessionRequest request, String email);

    TradeOperationResponse operateTrade(TradeOperationRequest request, String email);

    TradeOperationResponse[] tradeOperationList(OperationListRequest request, String email);
}
