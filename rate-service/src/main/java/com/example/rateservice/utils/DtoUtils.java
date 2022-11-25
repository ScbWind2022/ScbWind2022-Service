package com.example.rateservice.utils;

import com.example.rateservice.dto.AccountResponseDto;
import com.example.rateservice.dto.domestic.AccountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DtoUtils {
    public AccountResponseDto toAccountResponseDto(final AccountDto checkDto){
        final AccountResponseDto accountResponseDto = AccountResponseDto.builder()
                .id(checkDto.getId())
                .sum(checkDto.getSum())
                .userId(checkDto.getUserId())
                .currencyName(checkDto.getCurrencyName())
                .currencyId(checkDto.getCurrencyId())
                .currencyCharCode(checkDto.getCurrencyCharCode())
                .enabled(checkDto.isEnable())
                .build();
        return accountResponseDto;
    }
}
