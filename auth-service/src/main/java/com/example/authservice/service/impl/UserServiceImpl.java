package com.example.authservice.service.impl;

import com.example.authservice.dto.*;
import com.example.authservice.dto.domestic.JwtDto;
import com.example.authservice.dto.domestic.UserDto;
import com.example.authservice.exception.NotValidRequestException;
import com.example.authservice.exception.UserNotFoundException;
import com.example.authservice.grpcClient.UserGrpcClient;
import com.example.authservice.repository.RedisRepository;
import com.example.authservice.service.UserService;
import com.example.authservice.utils.DtoUtils;
import com.example.authservice.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final DtoUtils dtoUtils;
    private final UserGrpcClient userGrpcClient;
    private final RedisRepository redisRepository;
    private final JwtUtils jwtUtils;
    private void loginUserValid(UserDto userDTO){
        if(userDTO == null){
            throw new NotValidRequestException();
        }
        if(userDTO.getEmail() == null || userDTO.getPassword() == null){
            throw new NotValidRequestException();
        }
    }
    @Override
    public JwtDto loginUser(AuthRequestDto userDTO2) {
        final UserDto userDTO = dtoUtils.toUserDTO(userDTO2);
        loginUserValid(userDTO);
        final UserDto userDTO1 = userGrpcClient.loginUser(userDTO);
        if(!userDTO.getPassword().equals(userDTO1.getPassword())){
            throw new UserNotFoundException("Incorrect email or password");
        }
        // TODO save refresh in redis and add matches password
        return new JwtDto(jwtUtils.generateAccessToken(userDTO1),jwtUtils.generateRefreshToken(userDTO1));
    }

    @Override
    public JwtDto updateAccessAndRefreshToken(String refresh) {
        if(refresh == null){
            throw new NotValidRequestException("Not valid request");
        }
        final UserDto userDTO = jwtUtils.parseRefreshToken(refresh);
        /*final String hashToken = String.valueOf(refresh.hashCode());
        final String tokenRedis = redisRepository.getValue(userDTO.getEmail());*/
        // TODO add redis
        if(jwtUtils.validateRefreshToken(refresh)/* && hashToken.equals(tokenRedis)*/){
            final String newRefreshToken = jwtUtils.generateRefreshToken(userDTO);
            /*redisRepository.save(userDTO.getEmail(),String.valueOf(newRefreshToken.hashCode()));*/
            final String newAccessToken = jwtUtils.generateAccessToken(userDTO);
            return new JwtDto(newAccessToken,newRefreshToken);
        }
        throw new NotValidRequestException("Not valid request or incorrect token");
    }
    private void registerUserValid(UserDto userDTO){
        if(userDTO == null){
            throw new NotValidRequestException();
        }
        if(userDTO.getEmail() == null || userDTO.getPassword() == null){
            throw new NotValidRequestException();
        }
    }
    @Override
    public String registerUser(CreateUserRequest userDTO1) {
        final UserDto userDTO = dtoUtils.toUserDTO(userDTO1);
        registerUserValid(userDTO);
        //TODO set password encoder
        return userGrpcClient.registerUser(userDTO);
    }

    @Override
    public UserResponse getInfoUserByEmail(String email) {
        final UserDto req = UserDto.builder()
                .email(email)
                .build();
        final UserDto userDTO = userGrpcClient.getAccountUserByEmail(req);
        return dtoUtils.toUserResponse(userDTO);
    }
}
