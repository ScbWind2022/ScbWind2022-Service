package com.example.authservice.service;

import com.example.authservice.dto.*;
import com.example.authservice.dto.maindto.JwtDTO;

public interface UserService {
    JwtDTO loginUser(AuthRequestDto userDTO);
    JwtDTO updateAccessAndRefreshToken(String refreshToken);
    String registerUser(CreateUserRequest userDTO);
    UserResponse getInfoUserByEmail(String email);

}
