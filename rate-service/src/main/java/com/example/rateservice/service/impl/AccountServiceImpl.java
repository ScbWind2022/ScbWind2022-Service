package com.example.rateservice.service.impl;

import com.example.rateservice.dto.AccountResponseDto;
import com.example.rateservice.dto.maindto.CheckDto;
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
        final CheckDto[] checkDtos = accountGrpcClient.getAccountsUser(userDTO);
        final AccountResponseDto[] res = new AccountResponseDto[checkDtos.length];
        int index = 0;
        for (CheckDto c : checkDtos){
            res[index] = dtoUtils.toAccountResponseDto(c);
            index++;
        }
        return res;
    }

    @Override
    public AccountResponseDto changeSumInAccountByUserEmailAndId(String email, int checkId, BigDecimal sum) {
        final CheckDto req = CheckDto.builder()
                .userEmail(email)
                .id(checkId)
                .sum(String.valueOf(sum))
                .build();
        final CheckDto checkDto = accountGrpcClient.changeSumInSession(req);
        return dtoUtils.toAccountResponseDto(checkDto);
    }
}
