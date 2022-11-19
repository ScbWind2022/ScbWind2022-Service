package com.example.authservice.grpcClient.impl;

import Grpc.User;
import Grpc.UserServiceGrpc;
import com.example.authservice.dto.maindto.UserDTO;
import com.example.authservice.exception.NotValidRequestException;
import com.example.authservice.grpcClient.UserGrpcClient;

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
    public UserDTO loginUser(UserDTO userDTO) {
        try {
            final ListenableFuture<Grpc.User.getUserByEmailResponse> response = futureStub.getUserAndRoleByEmail(
                    User.getUserByEmailRequest.newBuilder()
                            .setRequest(gson.toJson(userDTO)).build());
            final User.getUserByEmailResponse res = response.get();
            switch (res.getStatus()){
                case(400):
                    throw new NotValidRequestException();
                default:
                    return gson.fromJson(res.getResponse(), UserDTO.class);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String registerUser(UserDTO userDTO) {
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
    public UserDTO[] getNotAcceptedUser() {
        try {
            final ListenableFuture<User.getNotAcceptedUserResponse> response = futureStub.getNotAcceptedUser(
                    User.getNotAcceptedUserRequest.newBuilder().build());
            final User.getNotAcceptedUserResponse res = response.get();

            return gson.fromJson(res.getResponse(),UserDTO[].class);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String acceptedUser(UserDTO userDTO) {
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
    public String bannedUser(UserDTO userDTO) {
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
    public UserDTO[] getBannedUser() {
        try {
            final ListenableFuture<User.getBannedUserResponse> response = futureStub.getBannedUser(
                    User.getBannedUserRequest.newBuilder().build());
            final User.getBannedUserResponse res = response.get();

            return gson.fromJson(res.getResponse(),UserDTO[].class);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public String removeBannedUser(UserDTO userDTO) {
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
    public UserDTO getAccountUserByEmail(UserDTO userDTO) {
        try {
            final ListenableFuture<User.getAccotuntUserByEmailResponse> response = futureStub.getAccountUserByEmail(
                    User.getAccountUserByEmailRequest.newBuilder()
                            .setRequest(gson.toJson(userDTO)).build());
            final User.getAccotuntUserByEmailResponse res = response.get();
            return gson.fromJson(res.getResponse(),UserDTO.class);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
