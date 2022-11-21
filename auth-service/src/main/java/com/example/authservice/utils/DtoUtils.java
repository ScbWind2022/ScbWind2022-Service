package com.example.authservice.utils;

import com.example.authservice.dto.AccountEnableRequest;
import com.example.authservice.dto.AccountRequest;
import com.example.authservice.dto.AccountResponse;
import com.example.authservice.dto.AuthRequestDto;
import com.example.authservice.dto.CreateUserRequest;
import com.example.authservice.dto.UserResponse;
import com.example.authservice.dto.domestic.AccountDto;
import com.example.authservice.dto.domestic.UserDto;
import org.springframework.stereotype.Service;

@Service
public class DtoUtils {
    public UserDto toUserDTO(final CreateUserRequest createUserRequest) {
        return UserDto.builder()
                .email(createUserRequest.getEmail())
                .firstName(createUserRequest.getFirstName())
                .password(createUserRequest.getPassword())
                .lastName(createUserRequest.getLastName())
                .phone(createUserRequest.getPhone())
                .build();
    }

    public UserDto toUserDTO(final AuthRequestDto authRequestDto) {
        return UserDto.builder()
                .email(authRequestDto.getEmail())
                .password(authRequestDto.getPassword())
                .build();
    }

    public UserResponse toUserResponse(final UserDto userDTO) {
        return UserResponse.builder()
                .lastName(userDTO.getLastName())
                .firstName(userDTO.getFirstName())
                .email(userDTO.getEmail())
                .phone(userDTO.getPhone())
                .id(userDTO.getId())
                .build();
    }

    public AccountResponse toAccountResponseDto(final AccountDto checkDto) {
        return AccountResponse.builder()
                .id(checkDto.getId())
                .sum(checkDto.getSum())
                .userId(checkDto.getUserId())
                .currencyName(checkDto.getCurrencyName())
                .currencyId(checkDto.getCurrencyId())
                .currencyCharCode(checkDto.getCurrencyCharCode())
                .enabled(checkDto.isEnable())
                .build();
    }

    public AccountDto toAccountDto(final AccountResponse accountResponseDto) {
        return AccountDto.builder()
                .enable(accountResponseDto.isEnabled())
                .currencyCharCode(accountResponseDto.getCurrencyCharCode())
                .currencyName(accountResponseDto.getCurrencyName())
                .currencyId(accountResponseDto.getCurrencyId())
                .sum(accountResponseDto.getSum())
                .id(accountResponseDto.getId())
                .build();
    }

    public AccountDto toAccountDto(final AccountRequest accountRequestDto) {
        return AccountDto.builder()
                .id(accountRequestDto.getId())
                .currencyCharCode(accountRequestDto.getCurrencyCharCode())
                .currencyId(accountRequestDto.getCurrencyId())
                .userId(accountRequestDto.getId())
                .sum(accountRequestDto.getSum())
                .build();
    }

    public AccountDto toAccountDto(final AccountEnableRequest request) {
        return AccountDto.builder()
                .id(request.getId())
                .enable(request.isEnable())
                .build();
    }
}
