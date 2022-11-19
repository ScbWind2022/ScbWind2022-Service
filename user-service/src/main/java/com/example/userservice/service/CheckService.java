package com.example.userservice.service;

import com.example.userservice.dto.CheckDto;
import com.example.userservice.dto.UserDTO;

import java.math.BigDecimal;

public interface CheckService {
    void createCheckWithUser(Long user_id);
    CheckDto openNewCheckWithUser(String userEmail);
    CheckDto[] getCheckByUserEmail(UserDTO userDTO);
    CheckDto changeSumByEmail(CheckDto checkDto);
    boolean changeEnableByEmail(CheckDto checkDto);
    CheckDto createCheck(CheckDto checkDto);
}
