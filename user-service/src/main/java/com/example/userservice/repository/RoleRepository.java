package com.example.userservice.repository;

import com.example.userservice.model.Role;

public interface RoleRepository {
    void save(Role role);
    void delete(Role role);
    Role getUserRole();
}
