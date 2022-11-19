package com.example.authservice.grpcClient;

import com.example.authservice.dto.maindto.UserDTO;

public interface UserGrpcClient {
    UserDTO loginUser(UserDTO userDTO);
    String registerUser(UserDTO userDTO);
    UserDTO[] getNotAcceptedUser();
    UserDTO[] getBannedUser();
    String acceptedUser(UserDTO userDTO);
    String bannedUser(UserDTO userDTO);
    String removeBannedUser(UserDTO userDTO);
    UserDTO getAccountUserByEmail(UserDTO userDTO);
}
