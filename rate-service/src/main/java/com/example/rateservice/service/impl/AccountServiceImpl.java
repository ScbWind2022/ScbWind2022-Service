package com.example.rateservice.service.impl;

import com.example.rateservice.dto.AccountResponseDto;
import com.example.rateservice.dto.maindto.AccountDto;
import com.example.rateservice.dto.maindto.UserDTO;
import com.example.rateservice.grpcclient.AccountGrpcClient;
import com.example.rateservice.service.AccountService;
import com.example.rateservice.utils.DtoUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final DtoUtils dtoUtils;
    private final AccountGrpcClient accountGrpcClient;
    @Override
    public AccountResponseDto[] getAccountsUserByEmail(String email) {
        final UserDTO userDTO = UserDTO.builder()
                .email(email)
                .build();
        final AccountDto[] AccountDtos = accountGrpcClient.getAccountsUser(userDTO);
        final AccountResponseDto[] res = new AccountResponseDto[AccountDtos.length];
        int index = 0;
        for (AccountDto c : AccountDtos){
            res[index] = dtoUtils.toAccountResponseDto(c);
            index++;
        }
        return res;
    }

    @Override
    public AccountResponseDto changeSumInAccountByUserEmailAndId(String email, int checkId, BigDecimal sum) {
        final AccountDto req = AccountDto.builder()
                .userEmail(email)
                .id(checkId)
                .sum(String.valueOf(sum))
                .build();
        final AccountDto accountDto = accountGrpcClient.changeSumInSession(req);
        return dtoUtils.toAccountResponseDto(accountDto);
    }

    @Override
    public String openSession(String email) {
        final UserDTO userDTO = UserDTO.builder()
                .email(email)
                .build();
        return accountGrpcClient.openSession(userDTO);
    }

    @Override
    public String closeSession(String email) {
        final UserDTO userDTO = UserDTO.builder()
                .email(email)
                .build();
        return accountGrpcClient.closeSession(userDTO);
    }

}
