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
    public void getAccountByEmail(Account.getAccountByUserEmailRequest request,
                                  StreamObserver<Account.getAccountByUserEmailResponse> responseObserver) {
        final Account.getAccountByUserEmailResponse response = Account.getAccountByUserEmailResponse.newBuilder()
                .setResponse(gson.toJson(accountService.getAccountByUserEmail(
                        gson.fromJson(request.getRequest(), UserDto.class)))).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void changeSum(Account.changeSumRequest request,
                          StreamObserver<Account.changeSumResponse> responseObserver) {
        final Account.changeSumResponse response = Account.changeSumResponse.newBuilder()
                .setResponse(gson.toJson(accountService.changeSumByEmail(
                        gson.fromJson(request.getRequest(), AccountDto.class)))).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void changeEnable(Account.changeEnableRequest request,
                             StreamObserver<Account.changeEnableResponse> responseObserver) {
        accountService.changeEnableByEmail(gson.fromJson(request.getRequest(), AccountDto.class));
        final Account.changeEnableResponse response = Account.changeEnableResponse.newBuilder()
                .setResponse("true").build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void createAccount(Account.createAccountRequest request,
                              StreamObserver<Account.createAccountResponse> responseObserver) {
        final Account.createAccountResponse response = Account.createAccountResponse.newBuilder()
                .setResponse(gson.toJson(accountService.createAccount(
                        gson.fromJson(request.getRequest(), AccountDto.class)))).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void changeSumInSession(Account.changeSumInSessionRequest request,
                                   StreamObserver<Account.chandeSumInSessionResponse> responseObserver) {
        final Account.chandeSumInSessionResponse response = Account.chandeSumInSessionResponse.newBuilder()
                .setResponse(gson.toJson(accountService.changeSumByEmailInSession(
                        gson.fromJson(request.getRequest(), AccountDto.class)))).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void openSession(Account.openSessionRequest request, StreamObserver<Account.openSessionResponse> responseObserver) {
        final Account.openSessionResponse response = Account.openSessionResponse.newBuilder()
                .setResponse(accountService.openSession(gson.fromJson(request.getRequest(), UserDto.class))).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void closeSession(Account.closeSessionRequest request, StreamObserver<Account.closeSessionResponse> responseObserver) {
        final Account.closeSessionResponse response = Account.closeSessionResponse.newBuilder()
                .setResponse(accountService.closeSession(gson.fromJson(request.getRequest(), UserDto.class))).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
