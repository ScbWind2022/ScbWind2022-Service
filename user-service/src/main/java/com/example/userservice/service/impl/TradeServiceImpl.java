package com.example.userservice.service.impl;

import com.example.userservice.dto.UserDto;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.service.TradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TradeServiceImpl implements TradeService {
    private final UserRepository userRepository;
    @Override
    public String openSession(UserDto userDTO) {
        userRepository.openSession(userDTO.getEmail());
        return "open";
    }

    @Override
    public String closeSession(UserDto userDTO) {
        userRepository.closeSession(userDTO.getEmail());
        return "close";
    }
}
