package com.example.userservice.service;

import com.example.userservice.dto.UserDTO;

public interface TradeService {
    String openSession(UserDTO userDTO);
    String closeSession(UserDTO userDTO);
}
