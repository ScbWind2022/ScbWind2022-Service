package com.example.userservice.repository;

import com.example.userservice.model.Role;
import com.example.userservice.model.User;

public interface UserRepository {
    void save(User user);
    void delete(User user);
    User getUserAndRoleByEmail(String email);
    User getUserByEmail(String email);
}
