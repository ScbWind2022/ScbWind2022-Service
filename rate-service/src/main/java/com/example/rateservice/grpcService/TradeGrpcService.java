package com.example.rateservice.grpcService;

import Grpc.Trade;
import Grpc.TradeServiceGrpc;
import com.example.rateservice.dto.OperationListRequest;
import com.example.rateservice.dto.TradeOperationRequest;
import com.example.rateservice.dto.TradeSessionRequest;
import com.example.rateservice.service.TradeService;
import com.google.gson.Gson;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@RequiredArgsConstructor
public class TradeGrpcService extends TradeServiceGrpc.TradeServiceImplBase {
    private final TradeService tradeService;
    private final Gson gson = new Gson();
    @Override
    public void operateTradeSession(Trade.operateTradeSessionRequest request, StreamObserver<Trade.operateTradeSessionResponse> responseObserver) {
        tradeService.operateTradeSession(gson.fromJson(request.getRequest(), TradeSessionRequest.class),request.getEmail());
        final Trade.operateTradeSessionResponse response = Trade.operateTradeSessionResponse.newBuilder()
                .setResponse("complete").build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void operateTrade(Trade.operateTradeRequest request, StreamObserver<Trade.operateTradeResponse> responseObserver) {
        final Trade.operateTradeResponse response = Trade.operateTradeResponse.newBuilder()
                .setResponse(gson.toJson(tradeService.operateTrade(gson.fromJson(
                        request.getRequest(), TradeOperationRequest.class), request.getEmail()))).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void tradeOperationList(Trade.tradeOperationListRequest request, StreamObserver<Trade.tradeOperationListResponse> responseObserver) {
        final Trade.tradeOperationListResponse response = Trade.tradeOperationListResponse.newBuilder()
                .setResponse(gson.toJson(tradeService.tradeOperationList
                        (gson.fromJson(request.getRequest(), OperationListRequest.class), request.getEmail()))).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
