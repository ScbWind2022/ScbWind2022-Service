package com.example.authservice.service;

import com.example.authservice.dto.domestic.UserDto;
import com.example.authservice.dto.UserResponse;

public interface AdminService {
    UserResponse[] getNotAcceptedUsers();
    UserResponse[] getBannedUser();
    String acceptedUser(UserDto userDTO);
    String bannedUser(UserDto userDTO);
    String removeBannedUser(UserDto userDTO);
}
