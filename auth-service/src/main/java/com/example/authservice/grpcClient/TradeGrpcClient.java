package com.example.authservice.grpcClient;

import com.example.authservice.dto.domestic.UserDto;

public interface TradeGrpcClient {
    String openSession(UserDto userDTO);
    String closeSession(UserDto userDTO);
    /*String operateTradeSession(TradeSessionRequest request,String email);
    TradeOperationResponse operateTrade(TradeOperationRequest request,String email);
    TradeOperationResponse[] tradeOperationList(OperationListRequest request,String email);*/
}
