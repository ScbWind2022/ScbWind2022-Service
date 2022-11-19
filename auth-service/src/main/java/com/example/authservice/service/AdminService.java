package com.example.authservice.service;

import com.example.authservice.dto.UserDTO;

public interface AdminService {
    UserDTO[] getNotAcceptedUsers();
    UserDTO[] getBannedUser();
    String acceptedUser(UserDTO userDTO);
    String bannedUser(UserDTO userDTO);
    String removeBannedUser(UserDTO userDTO);
}
