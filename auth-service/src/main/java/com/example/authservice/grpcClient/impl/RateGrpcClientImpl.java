package com.example.authservice.grpcClient.impl;

import Grpc.Rate;
import Grpc.RateServiceGrpc;
import com.example.authservice.dto.*;
import com.example.authservice.grpcClient.RateGrpcClient;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class RateGrpcClientImpl implements RateGrpcClient {
    private final Gson gson = new Gson();
    @GrpcClient("rate-service")
    private RateServiceGrpc.RateServiceFutureStub futureStub;

    @Override
    public CurrencyRateResponse getCurrentCurrencyRate(CurrentCurrencyRateRequest request) {
        try {
            final ListenableFuture<Rate.getCurrencyRateResponse> response = futureStub.getCurrentCurrencyRate(
                    Rate.getCurrencyRateRequest.newBuilder()
                            .setRequest(gson.toJson(request)).build());
            final Rate.getCurrencyRateResponse res = response.get();
            return gson.fromJson(res.getResponse(), CurrencyRateResponse.class);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public RangeCurrencyRateResponse getRangeCurrencyRate(RangeCurrencyRateRequest request) {
        try {
            final ListenableFuture<Rate.getRangeCurrencyRateResponse> response = futureStub.getRangeCurrencyRate(
                    Rate.getRangeCurrencyRateRequest.newBuilder()
                            .setRequest(gson.toJson(request)).build());
            final Rate.getRangeCurrencyRateResponse res = response.get();
            System.out.println(res.getResponse());
            return gson.fromJson(res.getResponse(), RangeCurrencyRateResponse.class);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public CurrencyResponse[] getCurrencyList() {
        try {
            final ListenableFuture<Rate.getListCurrencyResponse> responseListenableFuture = futureStub.getCurrencyList(
                    Rate.emptyRequest.newBuilder().getDefaultInstanceForType());
            final Rate.getListCurrencyResponse res = responseListenableFuture.get();
            return gson.fromJson(res.getResponse(), CurrencyResponse[].class);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
