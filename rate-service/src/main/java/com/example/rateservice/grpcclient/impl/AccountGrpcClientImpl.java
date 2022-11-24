package com.example.rateservice.grpcclient.impl;

import com.example.rateservice.dto.maindto.AccountDto;
import com.example.rateservice.dto.maindto.UserDTO;
import com.example.rateservice.grpcclient.AccountGrpcClient;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.gson.Gson;
import grpc.Account;
import grpc.AccountServiceGrpc;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class AccountGrpcClientImpl implements AccountGrpcClient {
    @GrpcClient("user-service")
    private AccountServiceGrpc.AccountServiceFutureStub futureStub;
    private final Gson gson = new Gson();

    @Override
    public AccountDto[] getAccountsUser(UserDTO userDTO) {
        try {
            final ListenableFuture<Account.getCheckByUserEmailResponse> response = futureStub.getCheckByEmail(
                    Account.getCheckByUserEmailRequest.newBuilder()
                            .setRequest(gson.toJson(userDTO)).build());
            final Account.getCheckByUserEmailResponse res = response.get();
            return gson.fromJson(res.getResponse(), AccountDto[].class);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public AccountDto changeSumInSession(AccountDto checkDto) {
        try {
            final ListenableFuture<Account.chandeSumInSessionResponse> response = futureStub.changeSumInSession(
                    Account.changeSumInSessionRequest.newBuilder()
                            .setRequest(gson.toJson(checkDto)).build());
            final Account.chandeSumInSessionResponse res = response.get();
            return gson.fromJson(res.getResponse(), AccountDto.class);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String openSession(UserDTO userDTO) {
        try {
            final ListenableFuture<Account.openSessionResponse> response = futureStub.openSession(
                    Account.openSessionRequest.newBuilder()
                            .setRequest(gson.toJson(userDTO)).build());
            final Account.openSessionResponse res = response.get();
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
            final ListenableFuture<Account.closeSessionResponse> response = futureStub.closeSession(
                    Account.closeSessionRequest.newBuilder()
                            .setRequest(gson.toJson(userDTO)).build());
            final Account.closeSessionResponse res = response.get();
            return res.getResponse();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

}
