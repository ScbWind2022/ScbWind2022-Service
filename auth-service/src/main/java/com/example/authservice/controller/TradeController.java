package com.example.authservice.controller;

import com.example.authservice.dto.OperationListRequest;
import com.example.authservice.dto.TradeOperationRequest;
import com.example.authservice.dto.TradeOperationResponse;
import com.example.authservice.dto.TradeSessionRequest;
import com.example.authservice.service.TradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping(value = "/api/v1/trade")
@RequiredArgsConstructor
public class TradeController {
    private final TradeService tradeService;
    @PostMapping(value = "/session")
    public ResponseEntity<String> session(@RequestBody TradeSessionRequest tradeSessionRequest,
                                          Principal principal){
        if(tradeSessionRequest.isEnable()){
            return new ResponseEntity<>(tradeService.openSession(principal.getName()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(tradeService.closeSession(principal.getName()),HttpStatus.OK);
        }
    }
    @PostMapping(value = "/api/v1/trade/operation")
    public ResponseEntity<TradeOperationResponse> tradeOperation(@RequestBody TradeOperationRequest request,
                                                                 Principal principal){
        return null;
    }
    @PostMapping(value = "/api/v1/trade/operation/list")
    public ResponseEntity<TradeOperationResponse> tradeList(@RequestBody OperationListRequest request,
                                                            Principal principal){
        return null;
    }
}
