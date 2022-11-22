package com.example.userservice.service;

import com.example.userservice.dto.AccountDto;
import com.example.userservice.dto.UserDto;

public interface AccountService {
    void createCheckWithUser(Long user_id);
    AccountDto openNewCheckWithUser(String userEmail);
    AccountDto[] getCheckByUserEmail(UserDto userDTO);
    AccountDto changeSumByEmail(AccountDto accountDto);
    boolean changeEnableByEmail(AccountDto accountDto);
    AccountDto createCheck(AccountDto accountDto);
    AccountDto changeSumByEmailInSession(AccountDto accountDto);
}
