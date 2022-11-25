package com.example.userservice.service.impl;

import com.example.userservice.dto.AccountDto;
import com.example.userservice.dto.UserDto;
import com.example.userservice.exception.AccountNotFoundException;
import com.example.userservice.exception.NotValidRequestException;
import com.example.userservice.exception.UserInSessionException;
import com.example.userservice.exception.UserNotFoundException;
import com.example.userservice.model.Account;
import com.example.userservice.model.User;
import com.example.userservice.repository.AccountRepository;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.service.AccountService;
import com.example.userservice.utils.DtoUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final DtoUtils dtoUtils;

    @Override
    public void createAccountWithUser(Long user_id) {
        final Account account = Account.builder()
                .checkToken(UUID.randomUUID().toString())
                .currencyCharCode("RUB")
                .sum(BigDecimal.ZERO)
                .enabled(true)
                .build();
        accountRepository.save(account);
        accountRepository.updateUserAndCheck(user_id, account.getId());
    }

    @Override
    public AccountDto openNewAccountWithUser(String userEmail) {
        final User user = userRepository.getUserByEmail(userEmail);
        if (user == null) {
            throw new UserNotFoundException();
        }
        final Account check = Account.builder()
                .checkToken(UUID.randomUUID().toString()).build();
        check.setUser(user);
        accountRepository.save(check);
        return dtoUtils.accountToAccountDto(check);
    }

    private void getCheckByUserEmailValid(UserDto userDTO) {
        if (userDTO == null) {
            throw new NotValidRequestException();
        }
        if (userDTO.getEmail() == null) {
            throw new NotValidRequestException();
        }
    }

    @Override
    public AccountDto[] getAccountByUserEmail(UserDto userDTO) {
        getCheckByUserEmailValid(userDTO);
        final List<Account> checks = accountRepository.getCheksByUserEmail(userDTO.getEmail());
        if (checks != null && checks.size() > 0) {
            final AccountDto[] checkDtos = new AccountDto[checks.size()];
            int index = 0;
            for (Account c : checks) {
                AccountDto checkDto = dtoUtils.accountToAccountDto(c);
                checkDto.setUserId(Integer.parseInt(String.valueOf(c.getUser().getId())));
                checkDtos[index] = checkDto;
                index++;
            }
            return checkDtos;
        }
        return new AccountDto[0];
    }

    @Override
    public AccountDto changeSumByEmail(AccountDto accountDto) {
        System.out.println(accountDto);
        final Long check_id = Long.parseLong(String.valueOf(accountDto.getId()));
        final String email = accountDto.getUserEmail();
        final BigDecimal sum = new BigDecimal(accountDto.getSum());

        final User user = userRepository.getUserByEmail(email);
        if (user.isInSession()) {
            throw new UserInSessionException();
        }
        final Account check1 = accountRepository.getCheckByIdAndUserEmail(check_id, email);
        if (check1 == null) {
            throw new AccountNotFoundException();
        }
        accountRepository.changeSumById(check_id, sum);
        final Account check = accountRepository.getCheckByIdAndUserEmail(check_id, email);
        if (check != null) {
            final AccountDto res = dtoUtils.accountToAccountDto(check);
            return res;
        }
        throw new AccountNotFoundException();
    }

    @Override
    public boolean changeEnableByEmail(AccountDto accountDto) {
        final User user = userRepository.getUserByEmail(accountDto.getUserEmail());
        if (user.isInSession()) {
            throw new UserInSessionException();
        }
        final Long check_id = Long.parseLong(String.valueOf(accountDto.getId()));
        return accountRepository.updateEnableByIdAndUserEmail(check_id, accountDto.getUserEmail(), accountDto.isEnable());
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        final Account check = dtoUtils.checkDtoToCheck(accountDto);
        check.setCheckToken(UUID.randomUUID().toString());
        check.setEnabled(true);
        final User user = userRepository.getUserByEmail(accountDto.getUserEmail());
        if (user == null) {
            throw new UserNotFoundException();
        }
        check.setUser(user);
        check.setUser(user);
        check.setSum(BigDecimal.ZERO);
        accountRepository.save(check);
        return dtoUtils.accountToAccountDto(check);
    }

    @Override
    public AccountDto changeSumByEmailInSession(AccountDto accountDto) {
        final Long account_id = Long.parseLong(String.valueOf(accountDto.getId()));
        final String email = accountDto.getUserEmail();
        final BigDecimal sum = new BigDecimal(accountDto.getSum());

        final Account account = accountRepository.getCheckByIdAndUserEmail(account_id, email);
        if (account == null) {
            throw new AccountNotFoundException();
        }

        if (account.getSum().equals(sum)) {
            return dtoUtils.accountToAccountDto(account);
        }

        accountRepository.changeSumById(account_id, sum);
        final Account check = accountRepository.getCheckByIdAndUserEmail(account_id, email);
        if (check != null) {
            return dtoUtils.accountToAccountDto(check);
        }
        return null;
    }

    @Override
    public String openSession(UserDto userDto) {
        userRepository.openSession(userDto.getEmail());
        return "open";
    }

    @Override
    public String closeSession(UserDto userDto) {
        userRepository.closeSession(userDto.getEmail());
        return "close";
    }
}
