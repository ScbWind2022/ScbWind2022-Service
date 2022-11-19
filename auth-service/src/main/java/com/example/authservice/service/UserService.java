package com.example.authservice.service;

import com.example.authservice.dto.JwtDTO;
import com.example.authservice.dto.UserDTO;

public interface UserService {
    JwtDTO loginUser(UserDTO userDTO);
    JwtDTO updateAccessAndRefreshToken(String refreshToken);
    String registerUser(UserDTO userDTO);

}
