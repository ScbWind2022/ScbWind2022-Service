package com.example.authservice.service.impl;

import com.example.authservice.dto.JwtDTO;
import com.example.authservice.dto.UserDTO;
import com.example.authservice.exception.NotValidRequestException;
import com.example.authservice.exception.UserNotFoundException;
import com.example.authservice.grpcClient.UserGrpcClient;
import com.example.authservice.repository.RedisRepository;
import com.example.authservice.service.UserService;
import com.example.authservice.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserGrpcClient userGrpcClient;
    private final RedisRepository redisRepository;
    private final JwtUtils jwtUtils;
    private void loginUserValid(UserDTO userDTO){
        if(userDTO == null){
            throw new NotValidRequestException();
        }
        if(userDTO.getEmail() == null || userDTO.getPassword() == null){
            throw new NotValidRequestException();
        }
    }
    @Override
    public JwtDTO loginUser(UserDTO userDTO) {
        loginUserValid(userDTO);
        final UserDTO userDTO1 = userGrpcClient.loginUser(userDTO);
        if(!userDTO.getPassword().equals(userDTO1.getPassword())){
            throw new UserNotFoundException("Incorrect email or password");
        }
        // TODO save refresh in redis and add matches password
        return new JwtDTO(jwtUtils.generateAccessToken(userDTO1),jwtUtils.generateRefreshToken(userDTO1));
    }

    @Override
    public JwtDTO updateAccessAndRefreshToken(String refresh) {
        if(refresh == null){
            throw new NotValidRequestException("Not valid request");
        }
        final UserDTO userDTO = jwtUtils.parseRefreshToken(refresh);
        /*final String hashToken = String.valueOf(refresh.hashCode());
        final String tokenRedis = redisRepository.getValue(userDTO.getEmail());*/
        // TODO add redis
        if(jwtUtils.validateRefreshToken(refresh)/* && hashToken.equals(tokenRedis)*/){
            final String newRefreshToken = jwtUtils.generateRefreshToken(userDTO);
            /*redisRepository.save(userDTO.getEmail(),String.valueOf(newRefreshToken.hashCode()));*/
            final String newAccessToken = jwtUtils.generateAccessToken(userDTO);
            return new JwtDTO(newAccessToken,newRefreshToken);
        }
        throw new NotValidRequestException("Not valid request or incorrect token");
    }
    private void registerUserValid(UserDTO userDTO){
        if(userDTO == null){
            throw new NotValidRequestException();
        }
        if(userDTO.getEmail() == null || userDTO.getPassword() == null){
            throw new NotValidRequestException();
        }
    }
    @Override
    public String registerUser(UserDTO userDTO) {
        registerUserValid(userDTO);
        //TODO set password encoder
        return userGrpcClient.registerUser(userDTO);
    }
}
