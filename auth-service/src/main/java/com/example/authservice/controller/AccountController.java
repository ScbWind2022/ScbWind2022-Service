package com.example.authservice.controller;

import com.example.authservice.dto.AccountRequest;
import com.example.authservice.dto.AccountResponse;
import com.example.authservice.dto.CreateAccountRequest;
import com.example.authservice.service.AccountService;
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
    private final AccountService accountService;

    @PostMapping(value = "/sum")
    public ResponseEntity<AccountResponse> changeSum(@RequestBody AccountRequest request,
                                                     Principal principal) {
        return new ResponseEntity<>(accountService.changeSumByEmail(request, principal.getName()),
                HttpStatus.OK);
    }

    @PostMapping(value = "/")
    public ResponseEntity<AccountResponse> createAccount(@RequestBody CreateAccountRequest request,
                                                         Principal principal) {
        return new ResponseEntity<>(accountService.createAccount(request, principal.getName()),
                HttpStatus.OK);
    }

}
