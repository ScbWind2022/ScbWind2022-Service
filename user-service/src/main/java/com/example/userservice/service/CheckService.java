package com.example.userservice.service;

import com.example.userservice.dto.CheckDto;

public interface CheckService {
    void createCheckWithUser(Long user_id);
    CheckDto openNewCheckWithUser(String userEmail);
}
