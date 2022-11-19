package com.example.authservice.grpcClient.impl;

import Grpc.Trade;
import Grpc.TradeServiceGrpc;
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
}
