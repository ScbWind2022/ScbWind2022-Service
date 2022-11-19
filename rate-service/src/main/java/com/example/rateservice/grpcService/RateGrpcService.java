package com.example.rateservice.grpcService;


import Grpc.Rate;
import Grpc.RateServiceGrpc;
import com.example.rateservice.dto.CurrencyRateRequest;
import com.example.rateservice.dto.RangeCurrencyRateRequest;
import com.example.rateservice.service.CurrencyRateService;
import com.google.gson.Gson;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@RequiredArgsConstructor
public class RateGrpcService extends RateServiceGrpc.RateServiceImplBase {

    private final CurrencyRateService currencyRateService;

    private final Gson gson = new Gson();

    @Override
    public void getCurrentCurrencyRate(Rate.getCurrencyRateRequest request,
                                       StreamObserver<Rate.getCurrencyRateResponse> responseObserver) {
        Rate.getCurrencyRateResponse response = Rate.getCurrencyRateResponse.newBuilder()
                .setResponse(gson.toJson(currencyRateService.getCurrentCurrencyRate(
                        gson.fromJson(request.getRequest(), CurrencyRateRequest.class)
                )))
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getRangeCurrencyRate(Rate.getRangeCurrencyRateRequest request,
                                     StreamObserver<Rate.getRangeCurrencyRateResponse> responseObserver) {
        Rate.getRangeCurrencyRateResponse response = Rate.getRangeCurrencyRateResponse.newBuilder()
                .setResponse(gson.toJson(currencyRateService.getRangeCurrencyRate(
                        gson.fromJson(request.getRequest(), RangeCurrencyRateRequest.class)
                )))
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getCurrencyList(Rate.emptyRequest request, StreamObserver<Rate.getListCurrencyResponse> responseObserver) {
        Rate.getListCurrencyResponse response = Rate.getListCurrencyResponse.newBuilder()
                .setResponse(gson.toJson(currencyRateService.getCurrencyList()))
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
