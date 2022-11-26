package com.example.rateservice.grpcservice;


import grpc.Rate;
import grpc.RateServiceGrpc;
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
    public void getCurrentCurrencyRate(Rate.CurrencyRateRequest request,
                                       StreamObserver<Rate.CurrencyRateResponse> responseObserver) {
        Rate.CurrencyRateResponse response = Rate.CurrencyRateResponse.newBuilder()
                .setResponse(gson.toJson(currencyRateService.getCurrentCurrencyRate(
                        gson.fromJson(request.getRequest(), CurrencyRateRequest.class)
                )))
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getRangeCurrencyRate(Rate.RangeCurrencyRateRequest request,
                                     StreamObserver<Rate.RangeCurrencyRateResponse> responseObserver) {
        Rate.RangeCurrencyRateResponse response = Rate.RangeCurrencyRateResponse.newBuilder()
                .setResponse(gson.toJson(currencyRateService.getRangeCurrencyRate(
                        gson.fromJson(request.getRequest(), RangeCurrencyRateRequest.class)
                )))
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getCurrencyList(Rate.emptyRequest request, StreamObserver<Rate.ListCurrencyResponse> responseObserver) {
        Rate.ListCurrencyResponse response = Rate.ListCurrencyResponse.newBuilder()
                .setResponse(gson.toJson(currencyRateService.getCurrencyList()))
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

}
