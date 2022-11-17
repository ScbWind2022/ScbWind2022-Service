package com.example.authservice.grpcClient;

import com.example.authservice.dto.UserDTO;

public interface UserGrpcClient {
    UserDTO loginUser(UserDTO userDTO);
    String registerUser(UserDTO userDTO);
}
