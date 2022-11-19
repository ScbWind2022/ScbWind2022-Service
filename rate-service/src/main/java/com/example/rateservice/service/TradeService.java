package com.example.rateservice.service;

import com.example.rateservice.dto.OperationListRequest;
import com.example.rateservice.dto.TradeOperationRequest;
import com.example.rateservice.dto.TradeOperationResponse;
import com.example.rateservice.dto.TradeSessionRequest;

import java.util.List;

public interface TradeService {

    void operateTradeSession(TradeSessionRequest request);

    TradeOperationResponse operateTrade(TradeOperationRequest request);

    List<TradeOperationResponse> tradeOperationList(OperationListRequest request);

}
