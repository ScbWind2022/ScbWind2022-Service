package com.example.authservice.service.impl;

import com.example.authservice.dto.maindto.UserDTO;
import com.example.authservice.dto.UserResponse;
import com.example.authservice.exception.NotValidRequestException;
import com.example.authservice.grpcClient.UserGrpcClient;
import com.example.authservice.service.AdminService;
import com.example.authservice.utils.DtoUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final UserGrpcClient userGrpcClient;
    private final DtoUtils dtoUtils;
    @Override
    public UserResponse[] getNotAcceptedUsers() {
        final UserDTO[] userDTOS = userGrpcClient.getNotAcceptedUser();
        final UserResponse[] userResponses = new UserResponse[userDTOS.length];
        int index = 0;
        for(UserDTO u : userDTOS){
            userResponses[index] = dtoUtils.toUserResponse(u);
            index++;
        }
        return userResponses;
    }
    private void acceptedUserValid(UserDTO userDTO){
        if(userDTO == null){
            throw new NotValidRequestException();
        }
        if(userDTO.getId() == null){
            throw new NotValidRequestException();
        }
    }
    @Override
    public String acceptedUser(UserDTO userDTO) {
        acceptedUserValid(userDTO);
        return userGrpcClient.acceptedUser(userDTO);
    }
    private void bannedUserValid(UserDTO userDTO){
        if(userDTO == null){
            throw new NotValidRequestException();
        }
        if(userDTO.getId() == null){
            throw new NotValidRequestException();
        }
    }
    @Override
    public String bannedUser(UserDTO userDTO) {
        bannedUserValid(userDTO);
        return userGrpcClient.bannedUser(userDTO);
    }

    @Override
    public UserResponse[] getBannedUser() {
        final UserDTO[] userDTOS = userGrpcClient.getBannedUser();
        final UserResponse[] userResponses = new UserResponse[userDTOS.length];
        int index = 0;
        for(UserDTO u : userDTOS){
            userResponses[index] = dtoUtils.toUserResponse(u);
            index++;
        }
        return userResponses;
    }
    private void removeBannedUserValid(UserDTO userDTO){
        if(userDTO == null){
            throw new NotValidRequestException();
        }
        if(userDTO.getId() == null){
            throw new NotValidRequestException();
        }
    }
    @Override
    public String removeBannedUser(UserDTO userDTO) {
        removeBannedUserValid(userDTO);
        return userGrpcClient.removeBannedUser(userDTO);
    }
}
