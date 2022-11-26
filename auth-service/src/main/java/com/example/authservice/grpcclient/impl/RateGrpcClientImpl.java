package com.example.authservice.grpcclient.impl;

import grpc.Rate;
import grpc.RateServiceGrpc;
import com.example.authservice.dto.*;
import com.example.authservice.grpcclient.RateGrpcClient;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

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
            final ListenableFuture<Rate.CurrencyRateResponse> response = futureStub.getCurrentCurrencyRate(
                    Rate.CurrencyRateRequest.newBuilder()
                            .setRequest(gson.toJson(request)).build());
            final Rate.CurrencyRateResponse res = response.get();
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
            final ListenableFuture<Rate.RangeCurrencyRateResponse> response = futureStub.getRangeCurrencyRate(
                    Rate.RangeCurrencyRateRequest.newBuilder()
                            .setRequest(gson.toJson(request)).build());
            final Rate.RangeCurrencyRateResponse res = response.get();
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
            final ListenableFuture<Rate.ListCurrencyResponse> responseListenableFuture = futureStub.getCurrencyList(
                    Rate.emptyRequest.newBuilder().getDefaultInstanceForType());
            final Rate.ListCurrencyResponse res = responseListenableFuture.get();
            return gson.fromJson(res.getResponse(), CurrencyResponse[].class);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
