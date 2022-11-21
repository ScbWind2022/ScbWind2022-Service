package com.example.userservice.service;

import com.example.userservice.dto.UserDto;

public interface TradeService {
    String openSession(UserDto userDTO);
    String closeSession(UserDto userDTO);
}
