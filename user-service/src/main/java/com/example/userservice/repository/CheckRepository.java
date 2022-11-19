package com.example.userservice.repository;

import com.example.userservice.model.Check;

import java.math.BigDecimal;
import java.util.List;

public interface CheckRepository {
    void save(Check check);
    void delete(Check check);
    void updateUserAndCheck(Long user_id,Long check_id);
    List<Check> getCheksByUserEmail(String email);
    Check getCheckByIdAndUserEmail(Long check_id,String email);
    boolean updateEnableByIdAndUserEmail(Long check_id,String email,boolean bol);
    void changeSumByEmailAndId(Long chek_id, String email, BigDecimal sum);
}
