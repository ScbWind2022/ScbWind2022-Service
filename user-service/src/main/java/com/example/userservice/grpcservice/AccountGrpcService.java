package com.example.userservice.grpcservice;

import com.example.userservice.dto.AccountDto;
import com.example.userservice.dto.UserDto;
import com.example.userservice.service.AccountService;
import com.google.gson.Gson;
import grpc.Account;
import grpc.AccountServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@RequiredArgsConstructor
public class AccountGrpcService extends AccountServiceGrpc.AccountServiceImplBase {
    private final Gson gson = new Gson();
    private final AccountService accountService;

    @Override
    public void getAccountByEmail(Account.AccountByUserEmailRequest request,
                                  StreamObserver<Account.AccountByUserEmailResponse> responseObserver) {
        final Account.AccountByUserEmailResponse response = Account.AccountByUserEmailResponse.newBuilder()
                .setResponse(gson.toJson(accountService.getAccountByUserEmail(
                        gson.fromJson(request.getRequest(), UserDto.class)))).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void changeSum(Account.ChangeSumRequest request,
                          StreamObserver<Account.ChangeSumResponse> responseObserver) {
        final Account.ChangeSumResponse response = Account.ChangeSumResponse.newBuilder()
                .setResponse(gson.toJson(accountService.changeSumByEmail(
                        gson.fromJson(request.getRequest(), AccountDto.class)))).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void changeEnable(Account.ChangeEnableRequest request,
                             StreamObserver<Account.ChangeEnableResponse> responseObserver) {
        accountService.changeEnableByEmail(gson.fromJson(request.getRequest(), AccountDto.class));
        final Account.ChangeEnableResponse response = Account.ChangeEnableResponse.newBuilder()
                .setResponse("true").build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void createAccount(Account.CreateAccountRequest request,
                              StreamObserver<Account.CreateAccountResponse> responseObserver) {
        final Account.CreateAccountResponse response = Account.CreateAccountResponse.newBuilder()
                .setResponse(gson.toJson(accountService.createAccount(
                        gson.fromJson(request.getRequest(), AccountDto.class)))).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void changeSumInSession(Account.ChangeSumInSessionRequest request,
                                   StreamObserver<Account.ChandeSumInSessionResponse> responseObserver) {
        final Account.ChandeSumInSessionResponse response = Account.ChandeSumInSessionResponse.newBuilder()
                .setResponse(gson.toJson(accountService.changeSumByEmailInSession(
                        gson.fromJson(request.getRequest(), AccountDto.class)))).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void openSession(Account.OpenSessionRequest request,
                            StreamObserver<Account.OpenSessionResponse> responseObserver) {
        final Account.OpenSessionResponse response = Account.OpenSessionResponse.newBuilder()
                .setResponse(accountService.openSession(gson.fromJson(request.getRequest(), UserDto.class))).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void closeSession(Account.CloseSessionRequest request,
                             StreamObserver<Account.CloseSessionResponse> responseObserver) {
        final Account.CloseSessionResponse response = Account.CloseSessionResponse.newBuilder()
                .setResponse(accountService.closeSession(gson.fromJson(request.getRequest(), UserDto.class))).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
