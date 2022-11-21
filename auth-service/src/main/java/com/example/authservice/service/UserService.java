package com.example.authservice.service;

import com.example.authservice.dto.*;
import com.example.authservice.dto.domestic.JwtDto;

public interface UserService {
    JwtDto loginUser(AuthRequestDto userDTO);
    JwtDto updateAccessAndRefreshToken(String refreshToken);
    String registerUser(CreateUserRequest userDTO);
    UserResponse getInfoUserByEmail(String email);

}
