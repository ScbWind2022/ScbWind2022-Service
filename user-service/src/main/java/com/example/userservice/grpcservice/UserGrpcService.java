package com.example.userservice.grpcservice;

import com.example.userservice.dto.UserDto;
import com.example.userservice.service.UserService;
import com.google.gson.Gson;
import grpc.User;
import grpc.UserServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@RequiredArgsConstructor
public class UserGrpcService extends UserServiceGrpc.UserServiceImplBase {
    private final UserService userService;
    private final Gson gson = new Gson();

    @Override
    public void getUserAndRoleByEmail(User.UserByEmailRequest request,
                                      StreamObserver<User.UserByEmailResponse> responseObserver) {
        final User.UserByEmailResponse response = User.UserByEmailResponse.newBuilder()
                .setResponse(gson.toJson(userService.getUserAndRoleByEmail(
                        gson.fromJson(request.getRequest(), UserDto.class).getEmail())))
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void registerUser(User.RegisterUserRequest request,
                             StreamObserver<User.RegisterUserResponse> responseObserver) {
        final User.RegisterUserResponse response = User.RegisterUserResponse.newBuilder()
                .setResponse(userService.registerUser(gson.fromJson(request.getRequest(), UserDto.class)))
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getNotAcceptedUser(User.NotAcceptedUserRequest request,
                                   StreamObserver<User.NotAcceptedUserResponse> responseObserver) {
        final User.NotAcceptedUserResponse response = User.NotAcceptedUserResponse.newBuilder()
                .setResponse(gson.toJson(userService.getNotAcceptedUser())).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void acceptedUser(User.AcceptedUserRequest request,
                             StreamObserver<User.AccepteduserResponse> responseObserver) {
        final User.AccepteduserResponse response = User.AccepteduserResponse.newBuilder()
                .setResponse(userService.acceptedUserById(gson.fromJson(request.getRequest(), UserDto.class))).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void bannedUser(User.BannedUserRequest request,
                           StreamObserver<User.BannedUserResponse> responseObserver) {
        final User.BannedUserResponse response = User.BannedUserResponse.newBuilder()
                .setResponse(userService.bannedUserById(gson.fromJson(request.getRequest(), UserDto.class))).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void removeBannedUser(User.RemoveBannedUserRequest request,
                                 StreamObserver<User.RemoveBannedUserResponse> responseObserver) {
        final User.RemoveBannedUserResponse response = User.RemoveBannedUserResponse.newBuilder()
                .setResponse(userService.removeBannedUser(gson.fromJson(request.getRequest(), UserDto.class))).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getBannedUser(User.BannedUserStatusRequest request,
                              StreamObserver<User.BannedUserStatusResponse> responseObserver) {
        final User.BannedUserStatusResponse response = User.BannedUserStatusResponse.newBuilder()
                .setResponse(gson.toJson(userService.getBannedUser())).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getAccountUserByEmail(User.AccountUserByEmailRequest request,
                                      StreamObserver<User.AccountUserByEmailResponse> responseObserver) {
        final User.AccountUserByEmailResponse response = User.AccountUserByEmailResponse.newBuilder()
                .setResponse(gson.toJson(userService.getUserAccountByEmail(
                        gson.fromJson(request.getRequest(), UserDto.class)))).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
