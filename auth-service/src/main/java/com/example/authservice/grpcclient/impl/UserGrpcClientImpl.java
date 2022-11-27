package com.example.authservice.grpcclient.impl;

import com.example.authservice.dto.domestic.UserDto;
import com.example.authservice.exception.NotValidRequestException;
import com.example.authservice.grpcclient.UserGrpcClient;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.gson.Gson;
import grpc.User;
import grpc.UserServiceGrpc;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class UserGrpcClientImpl implements UserGrpcClient {
    @GrpcClient("user-service")
    private UserServiceGrpc.UserServiceFutureStub futureStub;
    private final Gson gson;

    @Override
    public UserDto loginUser(UserDto userDTO) {
        try {
            final ListenableFuture<grpc.User.UserByEmailResponse> response = futureStub.getUserAndRoleByEmail(
                    User.UserByEmailRequest.newBuilder()
                            .setRequest(gson.toJson(userDTO)).build());
            final User.UserByEmailResponse res = response.get();
            switch (res.getStatus()) {
                case (400):
                    throw new NotValidRequestException();
                default:
                    return gson.fromJson(res.getResponse(), UserDto.class);
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String registerUser(UserDto userDTO) {
        try {
            final ListenableFuture<User.RegisterUserResponse> response = futureStub.registerUser(
                    User.RegisterUserRequest.newBuilder()
                            .setRequest(gson.toJson(userDTO)).build());
            final User.RegisterUserResponse res = response.get();

            return res.getResponse();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserDto[] getNotAcceptedUser() {
        try {
            final ListenableFuture<User.NotAcceptedUserResponse> response = futureStub.getNotAcceptedUser(
                    User.NotAcceptedUserRequest.newBuilder().build());
            final User.NotAcceptedUserResponse res = response.get();

            return gson.fromJson(res.getResponse(), UserDto[].class);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String acceptedUser(UserDto userDTO) {
        try {
            final ListenableFuture<User.AccepteduserResponse> response = futureStub.acceptedUser(
                    User.AcceptedUserRequest.newBuilder()
                            .setRequest(gson.toJson(userDTO)).build());
            final User.AccepteduserResponse res = response.get();

            return res.getResponse();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public String bannedUser(UserDto userDTO) {
        try {
            final ListenableFuture<User.BannedUserResponse> response = futureStub.bannedUser(
                    User.BannedUserRequest.newBuilder()
                            .setRequest(gson.toJson(userDTO)).build());
            final User.BannedUserResponse res = response.get();

            return res.getResponse();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserDto[] getBannedUser() {
        try {
            final ListenableFuture<User.BannedUserStatusResponse> response = futureStub.getBannedUser(
                    User.BannedUserStatusRequest.newBuilder().build());
            final User.BannedUserStatusResponse res = response.get();

            return gson.fromJson(res.getResponse(), UserDto[].class);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public String removeBannedUser(UserDto userDTO) {
        try {
            final ListenableFuture<User.RemoveBannedUserResponse> response = futureStub.removeBannedUser(
                    User.RemoveBannedUserRequest.newBuilder()
                            .setRequest(gson.toJson(userDTO)).build());

            final User.RemoveBannedUserResponse res = response.get();
            return res.getResponse();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserDto getAccountUserByEmail(UserDto userDTO) {
        try {
            final ListenableFuture<User.AccountUserByEmailResponse> response = futureStub.getAccountUserByEmail(
                    User.AccountUserByEmailRequest.newBuilder()
                            .setRequest(gson.toJson(userDTO)).build());
            final User.AccountUserByEmailResponse res = response.get();
            return gson.fromJson(res.getResponse(), UserDto.class);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
