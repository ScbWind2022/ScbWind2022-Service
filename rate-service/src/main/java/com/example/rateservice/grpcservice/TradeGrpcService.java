package com.example.rateservice.grpcservice;

import com.example.rateservice.dto.OperationListRequest;
import com.example.rateservice.dto.TradeOperationRequest;
import com.example.rateservice.dto.TradeSessionRequest;
import com.example.rateservice.service.TradeService;
import com.google.gson.Gson;
import grpc.Trade;
import grpc.TradeServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@RequiredArgsConstructor
public class TradeGrpcService extends TradeServiceGrpc.TradeServiceImplBase {
    private final TradeService tradeService;
    private final Gson gson = new Gson();

    @Override
    public void operateTradeSession(Trade.TradeSessionRequest request,
                                    StreamObserver<Trade.TradeSessionResponse> responseObserver) {
        tradeService.operateTradeSession(gson.fromJson(request.getRequest(), TradeSessionRequest.class),
                request.getEmail());
        final Trade.TradeSessionResponse response = Trade.TradeSessionResponse.newBuilder()
                .setResponse("complete").build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void operateTrade(Trade.TradeOperationRequest request,
                             StreamObserver<Trade.TradeOperationResponse> responseObserver) {
        final Trade.TradeOperationResponse response = Trade.TradeOperationResponse.newBuilder()
                .setResponse(gson.toJson(tradeService.operateTrade(
                                gson.fromJson(
                                        request.getRequest(),
                                        TradeOperationRequest.class),
                                request.getEmail()
                        )
                ))
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void tradeOperationList(Trade.TradeOperationListRequest request,
                                   StreamObserver<Trade.TradeOperationListResponse> responseObserver) {
        final Trade.TradeOperationListResponse response = Trade.TradeOperationListResponse.newBuilder()
                .setResponse(gson.toJson(tradeService.tradeOperationList(
                                gson.fromJson(request.getRequest(), OperationListRequest.class),
                                request.getEmail()
                        )
                ))
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
