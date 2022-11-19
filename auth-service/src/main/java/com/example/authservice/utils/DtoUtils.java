package com.example.authservice.utils;

import com.example.authservice.dto.*;
import com.example.authservice.dto.maindto.CheckDto;
import com.example.authservice.dto.maindto.UserDTO;
import org.springframework.stereotype.Service;

@Service
public class DtoUtils {
    public UserDTO toUserDTO(final CreateUserRequest createUserRequest){
        final UserDTO userDTO = UserDTO.builder()
                .email(createUserRequest.getEmail())
                .firstName(createUserRequest.getFirstName())
                .password(createUserRequest.getPassword())
                .lastName(createUserRequest.getLastName())
                .phone(createUserRequest.getPhone())
                .build();
        return userDTO;
    }
    public UserDTO toUserDTO(final AuthRequestDto authRequestDto){
        final UserDTO userDTO = UserDTO.builder()
                .email(authRequestDto.getEmail())
                .password(authRequestDto.getPassword())
                .build();
        return userDTO;
    }

    public UserResponse toUserResponse(final UserDTO userDTO){
        final UserResponse response = UserResponse.builder()
                .lastName(userDTO.getLastName())
                .firstName(userDTO.getFirstName())
                .email(userDTO.getEmail())
                .phone(userDTO.getPhone())
                .id(userDTO.getId())
                .build();
        return response;
    }
    public AccountResponseDto toAccountResponseDto(final CheckDto checkDto){
        final AccountResponseDto accountResponseDto = AccountResponseDto.builder()
                .id(checkDto.getId())
                .sum(checkDto.getSum())
                .userId(checkDto.getUserId())
                .currencyName(checkDto.getCurrencyName())
                .currencyId(checkDto.getCurrencyId())
                .currencyEngName(checkDto.getCurrencyEngName())
                .currencyCharCode(checkDto.getCurrencyCharCode())
                .enabled(checkDto.isEnable())
                .build();
        return accountResponseDto;
    }
    public CheckDto toCheckDto(final AccountResponseDto accountResponseDto){
        final CheckDto checkDto = CheckDto.builder()
                .enable(accountResponseDto.isEnabled())
                .currencyCharCode(accountResponseDto.getCurrencyCharCode())
                .currencyEngName(accountResponseDto.getCurrencyEngName())
                .currencyName(accountResponseDto.getCurrencyName())
                .currencyId(accountResponseDto.getCurrencyId())
                .sum(accountResponseDto.getSum())
                .id(accountResponseDto.getId())
                .build();
        return checkDto;
    }
    public CheckDto toCheckDto(final AccountRequestDto accountRequestDto){
        final CheckDto checkDto = CheckDto.builder()
                .id(accountRequestDto.getId())
                .currencyCharCode(accountRequestDto.getCurrencyCharCode())
                .userId(accountRequestDto.getId())
                .sum(accountRequestDto.getSum())
                .build();
        return checkDto;
    }
    public CheckDto toCheckDto(final UserEnableRequest userEnableRequest){
        final CheckDto checkDto = CheckDto.builder()
                .id(userEnableRequest.getId())
                .enable(userEnableRequest.isEnable())
                .build();
        return checkDto;
    }
}
