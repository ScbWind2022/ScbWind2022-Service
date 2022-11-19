package com.example.userservice.repository;

import com.example.userservice.model.Role;
import com.example.userservice.model.User;

import java.util.List;

public interface UserRepository {
    void save(User user);
    void delete(User user);
    User getUserAndRoleByEmail(String email);
    User getUserByEmail(String email);
    List<User> getNotAcceptedUser();
    List<User> getBannedUser();
    void acceptedUserById(Long id);
    void bannedUserById(Long id);
    void removeBannedUserById(Long id);
    void openSession(String email);
    void closeSession(String email);
}
