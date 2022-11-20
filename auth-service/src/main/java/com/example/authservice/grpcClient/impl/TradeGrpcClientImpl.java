package com.example.authservice.grpcClient.impl;

import Grpc.Trade;
import Grpc.TradeServiceGrpc;
import com.example.authservice.dto.OperationListRequest;
import com.example.authservice.dto.TradeOperationRequest;
import com.example.authservice.dto.TradeOperationResponse;
import com.example.authservice.dto.TradeSessionRequest;
import com.example.authservice.dto.maindto.UserDTO;
import com.example.authservice.grpcClient.TradeGrpcClient;
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
    private final Gson gson = new Gson();
    @Override
    public String openSession(UserDTO userDTO) {
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
    public String closeSession(UserDTO userDTO) {
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

    @Override
    public String operateTradeSession(TradeSessionRequest request, String email) {
        try {
            final ListenableFuture<Trade.operateTradeSessionResponse> response = futureStub.operateTradeSession(
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
            final ListenableFuture<Trade.operateTradeResponse> response = futureStub.operateTrade(
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
            final ListenableFuture<Trade.tradeOperationListResponse> response = futureStub.tradeOperationList(
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
    }
}
