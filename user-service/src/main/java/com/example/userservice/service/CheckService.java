package com.example.userservice.service;

import com.example.userservice.dto.CheckDto;
import com.example.userservice.dto.UserDTO;

public interface CheckService {
    void createCheckWithUser(Long user_id);
    CheckDto openNewCheckWithUser(String userEmail);
    CheckDto[] getCheckByUserEmail(UserDTO userDTO);
}
