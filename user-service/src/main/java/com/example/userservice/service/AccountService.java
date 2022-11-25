package com.example.userservice.service;

import com.example.userservice.dto.AccountDto;
import com.example.userservice.dto.UserDto;

public interface AccountService {
    void createAccountWithUser(Long user_id);
    AccountDto openNewAccountWithUser(String userEmail);
    AccountDto[] getAccountByUserEmail(UserDto userDTO);
    AccountDto changeSumByEmail(AccountDto accountDto);
    boolean changeEnableByEmail(AccountDto accountDto);
    AccountDto createAccount(AccountDto accountDto);
    AccountDto changeSumByEmailInSession(AccountDto accountDto);
    String openSession(UserDto userDTO);
    String closeSession(UserDto userDTO);
}
