package com.example.userservice.grpcService;

import Grpc.User;
import Grpc.UserServiceGrpc;
import com.example.userservice.dto.UserDTO;
import com.example.userservice.service.UserService;
import com.google.gson.Gson;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@RequiredArgsConstructor
public class UserGrpcService extends UserServiceGrpc.UserServiceImplBase {
    private final UserService userService;
    private final Gson gson = new Gson();
    @Override
    public void getUserAndRoleByEmail(User.getUserByEmailRequest request, StreamObserver<User.getUserByEmailResponse> responseObserver) {
        final User.getUserByEmailResponse response = User.getUserByEmailResponse.newBuilder()
                .setResponse(gson.toJson(userService.getUserAndRoleByEmail(
                        gson.fromJson(request.getRequest(), UserDTO.class).getEmail())))
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void registerUser(User.registerUserRequest request, StreamObserver<User.registerUserResponse> responseObserver) {
        final User.registerUserResponse response = User.registerUserResponse.newBuilder()
                .setResponse(userService.registerUser(gson.fromJson(request.getRequest(), UserDTO.class)))
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getNotAcceptedUser(User.getNotAcceptedUserRequest request, StreamObserver<User.getNotAcceptedUserResponse> responseObserver) {
        final User.getNotAcceptedUserResponse response = User.getNotAcceptedUserResponse.newBuilder()
                .setResponse(gson.toJson(userService.getNotAcceptedUser())).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void acceptedUser(User.acceptedUserRequest request, StreamObserver<User.accepteduserResponse> responseObserver) {
        final User.accepteduserResponse response = User.accepteduserResponse.newBuilder()
                .setResponse(userService.acceptedUserById(gson.fromJson(request.getRequest(), UserDTO.class))).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void bannedUser(User.bannedUserRequest request, StreamObserver<User.bannedUserResponse> responseObserver) {
        final User.bannedUserResponse response = User.bannedUserResponse.newBuilder()
                .setResponse(userService.bannedUserById(gson.fromJson(request.getRequest(), UserDTO.class))).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void removeBannedUser(User.removeBannedUserRequest request, StreamObserver<User.removeBannedUserResponse> responseObserver) {
        final User.removeBannedUserResponse response = User.removeBannedUserResponse.newBuilder()
                .setResponse(userService.removeBannedUser(gson.fromJson(request.getRequest(), UserDTO.class))).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getBannedUser(User.getBannedUserRequest request, StreamObserver<User.getBannedUserResponse> responseObserver) {
        final User.getBannedUserResponse response = User.getBannedUserResponse.newBuilder()
                .setResponse(gson.toJson(userService.getBannedUser())).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getAccountUserByEmail(User.getAccountUserByEmailRequest request, StreamObserver<User.getAccotuntUserByEmailResponse> responseObserver) {
        final User.getAccotuntUserByEmailResponse response = User.getAccotuntUserByEmailResponse.newBuilder()
                .setResponse(gson.toJson(userService.getUserAccountByEmail(
                        gson.fromJson(request.getRequest(),UserDTO.class)))).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
