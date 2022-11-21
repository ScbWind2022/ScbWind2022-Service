package com.example.userservice.service;

import com.example.userservice.dto.UserDto;

public interface UserService {
    UserDto getUserAndRoleByEmail(String email);
    String registerUser(UserDto userDTO);
    UserDto[] getNotAcceptedUser();
    UserDto[] getBannedUser();
    String acceptedUserById(UserDto userDTO);
    String bannedUserById(UserDto userDTO);
    String removeBannedUser(UserDto userDTO);
    UserDto getUserAccountByEmail(UserDto userDTO);

}
