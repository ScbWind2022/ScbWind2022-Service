package com.example.authservice.grpcclient;

import com.example.authservice.dto.domestic.UserDto;

public interface UserGrpcClient {
    UserDto loginUser(UserDto userDTO);
    String registerUser(UserDto userDTO);
    UserDto[] getNotAcceptedUser();
    UserDto[] getBannedUser();
    String acceptedUser(UserDto userDTO);
    String bannedUser(UserDto userDTO);
    String removeBannedUser(UserDto userDTO);
    UserDto getAccountUserByEmail(UserDto userDTO);
}
