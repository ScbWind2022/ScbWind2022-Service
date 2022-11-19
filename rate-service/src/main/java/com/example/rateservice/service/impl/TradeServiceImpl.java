package com.example.rateservice.service.impl;

import com.example.rateservice.dto.OperationListRequest;
import com.example.rateservice.dto.TradeOperationRequest;
import com.example.rateservice.dto.TradeOperationResponse;
import com.example.rateservice.dto.TradeSessionRequest;
import com.example.rateservice.service.TradeService;

import java.util.List;

public class TradeServiceImpl implements TradeService {
    @Override
    public void operateTradeSession(TradeSessionRequest request) {

    }

    @Override
    public TradeOperationResponse operateTrade(TradeOperationRequest request) {
        return null;
    }

    @Override
    public List<TradeOperationResponse> tradeOperationList(OperationListRequest request) {
        return null;
    }
}
