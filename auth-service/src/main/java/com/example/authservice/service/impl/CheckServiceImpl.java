package com.example.authservice.service.impl;

import com.example.authservice.dto.AccountRequestDto;
import com.example.authservice.dto.AccountResponseDto;
import com.example.authservice.dto.CreateAccountRequestDto;
import com.example.authservice.dto.UserEnableRequest;
import com.example.authservice.dto.maindto.CheckDto;
import com.example.authservice.dto.maindto.UserDTO;
import com.example.authservice.grpcClient.CheckGrpcClient;
import com.example.authservice.service.CheckService;
import com.example.authservice.utils.DtoUtils;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CheckServiceImpl implements CheckService {
    private final Gson gson = new Gson();
    private final CheckGrpcClient checkGrpcClient;
    private final DtoUtils dtoUtils;
    @Override
    public AccountResponseDto[] getCheckWithUserByEmail(String email) {
        final UserDTO req = UserDTO.builder().email(email).build();
        final CheckDto[] checkDtos = checkGrpcClient.getCheckByEmail(req);
        if(checkDtos != null && checkDtos.length > 0){
            final AccountResponseDto[] accountResponseDtos = new AccountResponseDto[checkDtos.length];
            int index = 0;
            for(CheckDto c : checkDtos){
                accountResponseDtos[index] = dtoUtils.toAccountResponseDto(c);
                index++;
            }
            return accountResponseDtos;
        }
        return new AccountResponseDto[0];
    }

    @Override
    public AccountResponseDto changeSumByEmail(AccountRequestDto accountRequestDto, String email) {
        final CheckDto req = dtoUtils.toCheckDto(accountRequestDto);
        req.setUserEmail(email);
        final CheckDto checkDto = checkGrpcClient.changeSumByIdAndEmail(req);
        return dtoUtils.toAccountResponseDto(checkDto);
    }

    @Override
    public AccountResponseDto createCheckByEmail(CreateAccountRequestDto createAccountRequestDto, String email) {
        final AccountRequestDto accountRequestDto = AccountRequestDto.builder()
                .currencyCharCode(createAccountRequestDto.getCurrencyCharCode()).build();
        final CheckDto req = dtoUtils.toCheckDto(accountRequestDto);
        req.setUserEmail(email);
        final CheckDto checkDto = checkGrpcClient.createCheckByEmail(req);
        return dtoUtils.toAccountResponseDto(checkDto);
    }

    @Override
    public String changeEnabledByEmailAndId(UserEnableRequest userEnableRequest,String email) {
        final CheckDto checkDto = dtoUtils.toCheckDto(userEnableRequest);
        checkDto.setUserEmail(email);
        return checkGrpcClient.changeEnableByIdAndEmail(checkDto);
    }
}
