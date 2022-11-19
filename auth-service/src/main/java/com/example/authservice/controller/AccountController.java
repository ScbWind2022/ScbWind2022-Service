package com.example.authservice.controller;

import com.example.authservice.dto.AccountRequestDto;
import com.example.authservice.dto.AccountResponseDto;
import com.example.authservice.dto.CreateAccountRequestDto;
import com.example.authservice.service.CheckService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/accounts")
public class AccountController {
    private final CheckService checkService;
    @PostMapping(value = "/sum")
    public ResponseEntity<AccountResponseDto> changeSum(@RequestBody AccountRequestDto accountRequestDto,
                                                        Principal principal){
        return new ResponseEntity<>(
                checkService.changeSumByEmail(accountRequestDto, principal.getName()), HttpStatus.OK);
    }
    @PostMapping(value = "/")
    public ResponseEntity<AccountResponseDto> createCheck(@RequestBody CreateAccountRequestDto accountRequestDto,
                                                          Principal principal){
        return new ResponseEntity<>(checkService.createCheckByEmail(accountRequestDto, principal.getName()),HttpStatus.OK);
    }

}
