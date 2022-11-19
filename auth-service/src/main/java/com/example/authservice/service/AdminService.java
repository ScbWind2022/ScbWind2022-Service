package com.example.authservice.service;

import com.example.authservice.dto.maindto.UserDTO;
import com.example.authservice.dto.UserResponse;

public interface AdminService {
    UserResponse[] getNotAcceptedUsers();
    UserResponse[] getBannedUser();
    String acceptedUser(UserDTO userDTO);
    String bannedUser(UserDTO userDTO);
    String removeBannedUser(UserDTO userDTO);
}
