package com.example.authservice.grpcclient.impl;

import com.example.authservice.dto.OperationListRequest;
import com.example.authservice.dto.TradeOperationRequest;
import com.example.authservice.dto.TradeOperationResponse;
import com.example.authservice.dto.TradeSessionRequest;
import com.example.authservice.grpcclient.TradeGrpcClient;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.gson.Gson;
import grpc.Trade;
import grpc.TradeServiceGrpc;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class TradeGrpcClientImpl implements TradeGrpcClient {
    @GrpcClient("rate-service")
    private TradeServiceGrpc.TradeServiceFutureStub futureStub;
    private final Gson gson = new Gson();

    @Override
    public String operateTradeSession(TradeSessionRequest request, String email) {
        try {
            final ListenableFuture<Trade.TradeSessionResponse> future = futureStub.operateTradeSession(
                    Trade.TradeSessionRequest.newBuilder()
                            .setRequest(gson.toJson(request))
                            .setEmail(email).build());
            final Trade.TradeSessionResponse response = future.get();
            return response.getResponse();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public TradeOperationResponse operateTrade(TradeOperationRequest request, String email) {
        try {
            final ListenableFuture<Trade.TradeOperationResponse> future = futureStub.operateTrade(
                    Trade.TradeOperationRequest.newBuilder()
                            .setRequest(gson.toJson(request))
                            .setEmail(email).build());
            final Trade.TradeOperationResponse response = future.get();
            return gson.fromJson(response.getResponse(), TradeOperationResponse.class);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public TradeOperationResponse[] tradeOperationList(OperationListRequest request, String email) {
        try {
            final ListenableFuture<Trade.TradeOperationListResponse> future = futureStub.tradeOperationList(
                    Trade.TradeOperationListRequest.newBuilder()
                            .setRequest(gson.toJson(request))
                            .setEmail(email).build());
            final Trade.TradeOperationListResponse response = future.get();
            return gson.fromJson(response.getResponse(), TradeOperationResponse[].class);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

}
