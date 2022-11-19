package com.example.userservice.service;

import com.example.userservice.dto.UserDTO;

public interface UserService {
    UserDTO getUserAndRoleByEmail(String email);
    String registerUser(UserDTO userDTO);
    UserDTO[] getNotAcceptedUser();
    UserDTO[] getBannedUser();
    String acceptedUserById(UserDTO userDTO);
    String bannedUserById(UserDTO userDTO);
    String removeBannedUser(UserDTO userDTO);
    UserDTO getUserAccountByEmail(UserDTO userDTO);

}
