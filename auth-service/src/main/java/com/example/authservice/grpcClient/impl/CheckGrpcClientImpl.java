package com.example.authservice.grpcClient.impl;

import Grpc.Check;
import Grpc.CheckServiceGrpc;
import com.example.authservice.dto.maindto.CheckDto;
import com.example.authservice.dto.maindto.UserDTO;
import com.example.authservice.grpcClient.CheckGrpcClient;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class CheckGrpcClientImpl implements CheckGrpcClient {
    @GrpcClient("user-service")
    private CheckServiceGrpc.CheckServiceFutureStub futureStub;
    private final Gson gson = new Gson();
    @Override
    public CheckDto[] getCheckByEmail(UserDTO userDTO) {
        try {
            final ListenableFuture<Check.getCheckByUserEmailResponse> response = futureStub.getCheckByEmail(
                    Check.getCheckByUserEmailRequest.newBuilder()
                            .setRequest(gson.toJson(userDTO)).build());
            final Check.getCheckByUserEmailResponse res = response.get();
            return gson.fromJson(res.getResponse(), CheckDto[].class);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public CheckDto changeSumByIdAndEmail(CheckDto checkDto) {
        try {
            final ListenableFuture<Check.changeSumResponse> response = futureStub.changeSum(
                    Check.changeSumRequest.newBuilder()
                            .setRequest(gson.toJson(checkDto)).build());
            final Check.changeSumResponse res = response.get();
            return gson.fromJson(res.getResponse(), CheckDto.class);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String changeEnableByIdAndEmail(CheckDto checkDto) {
        try {
            final ListenableFuture<Check.changeEnableResponse> response = futureStub.changeEnable(
                    Check.changeEnableRequest.newBuilder()
                            .setRequest(gson.toJson(checkDto)).build());
            final Check.changeEnableResponse res = response.get();
            return res.getResponse();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public CheckDto createCheckByEmail(CheckDto checkDto) {
        try {
            final ListenableFuture<Check.createCheckResponse> response = futureStub.createCheck(
                    Check.createCheckRequest.newBuilder()
                            .setRequest(gson.toJson(checkDto)).build());
            final Check.createCheckResponse res = response.get();
            return gson.fromJson(res.getResponse(), CheckDto.class);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
