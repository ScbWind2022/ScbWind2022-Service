package com.example.authservice.grpcclient.impl;

import grpc.Trade;
import grpc.TradeServiceGrpc;
import com.example.authservice.dto.domestic.UserDto;
import com.example.authservice.grpcclient.TradeGrpcClient;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class TradeGrpcClientImpl implements TradeGrpcClient {
    @GrpcClient("user-service")
    private TradeServiceGrpc.TradeServiceFutureStub futureStub;
    @GrpcClient("rate-service")
    private TradeServiceGrpc.TradeServiceFutureStub tradeServiceFutureStub;
    private final Gson gson = new Gson();
    @Override
    public String openSession(UserDto userDTO) {
        try {
            final ListenableFuture<Trade.openSessionResponse> response = futureStub.openSession(
                    Trade.openSessionRequest.newBuilder()
                            .setRequest(gson.toJson(userDTO)).build());
            final Trade.openSessionResponse res = response.get();
            return res.getResponse();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String closeSession(UserDto userDTO) {
        try {
            final ListenableFuture<Trade.closeSessionResponse> response = futureStub.closeSession(
                    Trade.closeSessionRequest.newBuilder()
                            .setRequest(gson.toJson(userDTO)).build());
            final Trade.closeSessionResponse res = response.get();
            return res.getResponse();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    /*@Override
    public String operateTradeSession(TradeSessionRequest request, String email) {
        try {
            final ListenableFuture<Trade.operateTradeSessionResponse> response = tradeServiceFutureStub.operateTradeSession(
                    Trade.operateTradeSessionRequest.newBuilder()
                            .setRequest(gson.toJson(request))
                            .setEmail(email).build());
            final Trade.operateTradeSessionResponse res = response.get();
            return res.getResponse();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public TradeOperationResponse operateTrade(TradeOperationRequest request, String email) {
        try {
            final ListenableFuture<Trade.operateTradeResponse> response = tradeServiceFutureStub.operateTrade(
                    Trade.operateTradeRequest.newBuilder()
                            .setRequest(gson.toJson(request))
                            .setEmail(email).build());
            final Trade.operateTradeResponse res = response.get();
            return gson.fromJson(res.getResponse(), TradeOperationResponse.class);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public TradeOperationResponse[] tradeOperationList(OperationListRequest request, String email) {
        try {
            final ListenableFuture<Trade.tradeOperationListResponse> response = tradeServiceFutureStub.tradeOperationList(
                    Trade.tradeOperationListRequest.newBuilder()
                            .setRequest(gson.toJson(request))
                            .setEmail(email).build());
            final Trade.tradeOperationListResponse res = response.get();
            return gson.fromJson(res.getResponse(),TradeOperationResponse[].class);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }*/
}
