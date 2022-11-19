package com.example.userservice.service.impl;

import com.example.userservice.dto.UserDTO;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.service.TradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TradeServiceImpl implements TradeService {
    private final UserRepository userRepository;
    @Override
    public String openSession(UserDTO userDTO) {
        userRepository.openSession(userDTO.getEmail());
        return "open";
    }

    @Override
    public String closeSession(UserDTO userDTO) {
        userRepository.closeSession(userDTO.getEmail());
        return "close";
    }
}
