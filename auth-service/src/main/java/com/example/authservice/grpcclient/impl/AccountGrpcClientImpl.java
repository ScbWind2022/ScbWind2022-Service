package com.example.authservice.grpcclient.impl;

import grpc.Account;
import grpc.AccountServiceGrpc;
import com.example.authservice.dto.domestic.AccountDto;
import com.example.authservice.dto.domestic.UserDto;
import com.example.authservice.grpcclient.AccountGrpcClient;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.gson.Gson;
import grpc.Trade;
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
    public AccountDto[] getAccountByEmail(UserDto userDTO) {
        try {
            final ListenableFuture<Account.AccountByUserEmailResponse> response = futureStub.getAccountByEmail(
                    Account.AccountByUserEmailRequest.newBuilder()
                            .setRequest(gson.toJson(userDTO)).build());
            final Account.AccountByUserEmailResponse res = response.get();
            return gson.fromJson(res.getResponse(), AccountDto[].class);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public AccountDto changeSumByIdAndEmail(AccountDto accountDto) {
        try {
            final ListenableFuture<Account.ChangeSumResponse> response = futureStub.changeSum(
                    Account.ChangeSumRequest.newBuilder()
                            .setRequest(gson.toJson(accountDto)).build());
            final Account.ChangeSumResponse res = response.get();
            return gson.fromJson(res.getResponse(), AccountDto.class);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String changeEnableByIdAndEmail(AccountDto accountDto) {
        try {
            final ListenableFuture<Account.ChangeEnableResponse> response = futureStub.changeEnable(
                    Account.ChangeEnableRequest.newBuilder()
                            .setRequest(gson.toJson(accountDto)).build());
            final Account.ChangeEnableResponse res = response.get();
            return res.getResponse();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public AccountDto createAccountByEmail(AccountDto accountDto) {
        try {
            final ListenableFuture<Account.CreateAccountResponse> response = futureStub.createAccount(
                    Account.CreateAccountRequest.newBuilder()
                            .setRequest(gson.toJson(accountDto)).build());
            final Account.CreateAccountResponse res = response.get();
            return gson.fromJson(res.getResponse(), AccountDto.class);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

}
