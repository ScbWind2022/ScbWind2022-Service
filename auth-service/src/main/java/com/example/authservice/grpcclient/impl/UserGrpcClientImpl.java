package com.example.authservice.grpcclient.impl;

import grpc.User;
import grpc.UserServiceGrpc;
import com.example.authservice.dto.domestic.UserDto;
import com.example.authservice.exception.NotValidRequestException;
import com.example.authservice.grpcclient.UserGrpcClient;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.gson.Gson;
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
            final ListenableFuture<grpc.User.getUserByEmailResponse> response = futureStub.getUserAndRoleByEmail(
                    User.getUserByEmailRequest.newBuilder()
                            .setRequest(gson.toJson(userDTO)).build());
            final User.getUserByEmailResponse res = response.get();
            switch (res.getStatus()){
                case(400):
                    throw new NotValidRequestException();
                default:
                    return gson.fromJson(res.getResponse(), UserDto.class);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String registerUser(UserDto userDTO) {
        try {
            final ListenableFuture<User.registerUserResponse> response = futureStub.registerUser(
                    User.registerUserRequest.newBuilder()
                            .setRequest(gson.toJson(userDTO)).build());
            final User.registerUserResponse res = response.get();

            return res.getResponse();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserDto[] getNotAcceptedUser() {
        try {
            final ListenableFuture<User.getNotAcceptedUserResponse> response = futureStub.getNotAcceptedUser(
                    User.getNotAcceptedUserRequest.newBuilder().build());
            final User.getNotAcceptedUserResponse res = response.get();

            return gson.fromJson(res.getResponse(), UserDto[].class);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String acceptedUser(UserDto userDTO) {
        try {
            final ListenableFuture<User.accepteduserResponse> response = futureStub.acceptedUser(
                    User.acceptedUserRequest.newBuilder()
                            .setRequest(gson.toJson(userDTO)).build());
            final User.accepteduserResponse res = response.get();

            return res.getResponse();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public String bannedUser(UserDto userDTO) {
        try {
            final ListenableFuture<User.bannedUserResponse> response = futureStub.bannedUser(
                    User.bannedUserRequest.newBuilder()
                            .setRequest(gson.toJson(userDTO)).build());
            final User.bannedUserResponse res = response.get();

            return res.getResponse();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserDto[] getBannedUser() {
        try {
            final ListenableFuture<User.getBannedUserResponse> response = futureStub.getBannedUser(
                    User.getBannedUserRequest.newBuilder().build());
            final User.getBannedUserResponse res = response.get();

            return gson.fromJson(res.getResponse(), UserDto[].class);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public String removeBannedUser(UserDto userDTO) {
        try {
            final ListenableFuture<User.removeBannedUserResponse> response = futureStub.removeBannedUser(
                    User.removeBannedUserRequest.newBuilder()
                            .setRequest(gson.toJson(userDTO)).build());

            final User.removeBannedUserResponse res = response.get();
            return res.getResponse();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserDto getAccountUserByEmail(UserDto userDTO) {
        try {
            final ListenableFuture<User.getAccotuntUserByEmailResponse> response = futureStub.getAccountUserByEmail(
                    User.getAccountUserByEmailRequest.newBuilder()
                            .setRequest(gson.toJson(userDTO)).build());
            final User.getAccotuntUserByEmailResponse res = response.get();
            return gson.fromJson(res.getResponse(), UserDto.class);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
