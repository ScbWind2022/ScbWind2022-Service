package com.example.authservice.grpcClient;

import com.example.authservice.dto.OperationListRequest;
import com.example.authservice.dto.TradeOperationRequest;
import com.example.authservice.dto.TradeOperationResponse;
import com.example.authservice.dto.TradeSessionRequest;
import com.example.authservice.dto.maindto.UserDTO;

public interface TradeGrpcClient {
    String openSession(UserDTO userDTO);
    String closeSession(UserDTO userDTO);
    /*String operateTradeSession(TradeSessionRequest request,String email);
    TradeOperationResponse operateTrade(TradeOperationRequest request,String email);
    TradeOperationResponse[] tradeOperationList(OperationListRequest request,String email);*/
}
