package com.example.authservice.service.impl;

import com.example.authservice.dto.UserDTO;
import com.example.authservice.exception.NotValidRequestException;
import com.example.authservice.grpcClient.UserGrpcClient;
import com.example.authservice.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final UserGrpcClient userGrpcClient;
    @Override
    public UserDTO[] getNotAcceptedUsers() {
        return userGrpcClient.getNotAcceptedUser();
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
    public UserDTO[] getBannedUser() {
        return userGrpcClient.getBannedUser();
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
