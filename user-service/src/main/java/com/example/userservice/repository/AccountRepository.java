package com.example.userservice.repository;

import com.example.userservice.model.Account;

import java.math.BigDecimal;
import java.util.List;

public interface AccountRepository {
    void save(Account check);

    void delete(Account check);

    void updateUserAndCheck(Long user_id, Long check_id);

    List<Account> getCheksByUserEmail(String email);

    Account getCheckByIdAndUserEmail(Long check_id, String email);

    boolean updateEnableByIdAndUserEmail(Long check_id, String email, boolean bol);

    void changeSumById(Long chek_id, BigDecimal sum);
}
