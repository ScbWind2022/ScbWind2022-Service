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
}
