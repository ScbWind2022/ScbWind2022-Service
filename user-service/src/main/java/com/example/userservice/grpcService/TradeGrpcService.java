package com.example.userservice.grpcService;

import Grpc.Trade;
import Grpc.TradeServiceGrpc;
import com.example.userservice.dto.UserDTO;
import com.example.userservice.service.TradeService;
import com.google.gson.Gson;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@RequiredArgsConstructor
public class TradeGrpcService extends TradeServiceGrpc.TradeServiceImplBase {
    private final Gson gson = new Gson();
    private final TradeService tradeService;
    @Override
    public void openSession(Trade.openSessionRequest request, StreamObserver<Trade.openSessionResponse> responseObserver) {
        final Trade.openSessionResponse response = Trade.openSessionResponse.newBuilder()
                .setResponse(tradeService.openSession(gson.fromJson(request.getRequest(), UserDTO.class))).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void closeSession(Trade.closeSessionRequest request, StreamObserver<Trade.closeSessionResponse> responseObserver) {
        final Trade.closeSessionResponse response = Trade.closeSessionResponse.newBuilder()
                .setResponse(tradeService.closeSession(gson.fromJson(request.getRequest(), UserDTO.class))).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
