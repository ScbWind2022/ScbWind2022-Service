package com.example.authservice.service;

import com.example.authservice.dto.TradeOperationRequest;
import com.example.authservice.dto.TradeOperationResponse;
import com.example.authservice.dto.TradeSessionRequest;

public interface TradeService {
    String openSession(String email);
    String closeSession(String email);
    TradeOperationResponse operationTrade(TradeOperationRequest request);
}
