package com.example.userservice.repository;

import com.example.userservice.model.Check;

public interface CheckRepository {
    void save(Check check);
    void delete(Check check);
    void updateUserAndCheck(Long user_id,Long check_id);
}
