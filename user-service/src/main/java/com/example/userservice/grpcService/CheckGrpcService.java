package com.example.userservice.grpcService;

import Grpc.Check;
import Grpc.CheckServiceGrpc;
import com.example.userservice.dto.CheckDto;
import com.example.userservice.dto.UserDTO;
import com.example.userservice.service.CheckService;
import com.google.gson.Gson;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@RequiredArgsConstructor
public class CheckGrpcService extends CheckServiceGrpc.CheckServiceImplBase {
    private final Gson gson = new Gson();
    private final CheckService checkService;
    @Override
    public void getCheckByEmail(Check.getCheckByUserEmailRequest request, StreamObserver<Check.getCheckByUserEmailResponse> responseObserver) {
        final Check.getCheckByUserEmailResponse response = Check.getCheckByUserEmailResponse.newBuilder()
                .setResponse(gson.toJson(checkService.getCheckByUserEmail(
                        gson.fromJson(request.getRequest(), UserDTO.class)))).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void changeSum(Check.changeSumRequest request, StreamObserver<Check.changeSumResponse> responseObserver) {
        final Check.changeSumResponse response = Check.changeSumResponse.newBuilder()
                .setResponse(gson.toJson(checkService.changeSumByEmail(
                        gson.fromJson(request.getRequest(), CheckDto.class)))).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void changeEnable(Check.changeEnableRequest request, StreamObserver<Check.changeEnableResponse> responseObserver) {
        checkService.changeEnableByEmail(gson.fromJson(request.getRequest(), CheckDto.class));
        final Check.changeEnableResponse response = Check.changeEnableResponse.newBuilder()
                .setResponse("true").build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void createCheck(Check.createCheckRequest request, StreamObserver<Check.createCheckResponse> responseObserver) {
        final Check.createCheckResponse response = Check.createCheckResponse.newBuilder()
                .setResponse(gson.toJson(checkService.createCheck(
                        gson.fromJson(request.getRequest(), CheckDto.class)))).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
