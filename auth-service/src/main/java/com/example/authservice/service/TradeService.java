package com.example.authservice.service;

import com.example.authservice.dto.TradeSessionRequest;

public interface TradeService {
    String openSession(String email);
    String closeSession(String email);
}
