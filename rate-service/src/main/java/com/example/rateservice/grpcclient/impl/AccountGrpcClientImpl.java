package com.example.rateservice.grpcclient.impl;

import Grpc.Check;
import Grpc.CheckServiceGrpc;
import com.example.rateservice.dto.maindto.CheckDto;
import com.example.rateservice.dto.maindto.UserDTO;
import com.example.rateservice.grpcclient.AccountGrpcClient;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class AccountGrpcClientImpl implements AccountGrpcClient {
    @GrpcClient("user-service")
    private CheckServiceGrpc.CheckServiceFutureStub futureStub;
    private final Gson gson = new Gson();

    @Override
    public CheckDto[] getAccountsUser(UserDTO userDTO) {
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
    public CheckDto changeSumInSession(CheckDto checkDto) {
        try {
            final ListenableFuture<Check.chandeSumInSessionResponse> response = futureStub.changeSumInSession(
                    Check.changeSumInSessionRequest.newBuilder()
                            .setRequest(gson.toJson(checkDto)).build());
            final Check.chandeSumInSessionResponse res = response.get();
            return gson.fromJson(res.getResponse(), CheckDto.class);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
