package com.example.userservice.service.impl;

import com.example.userservice.dto.UserDTO;
import com.example.userservice.exception.NotValidRequestException;
import com.example.userservice.exception.UserAlreadyExistException;
import com.example.userservice.exception.UserNotFoundException;
import com.example.userservice.model.Role;
import com.example.userservice.model.User;
import com.example.userservice.repository.RoleRepository;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.service.UserService;
import com.example.userservice.utils.DtoUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final DtoUtils dtoUtils;
    @Override
    public UserDTO getUserAndRoleByEmail(String email) {
        if(email == null){
            throw new NotValidRequestException();
        }
        final User user = userRepository.getUserAndRoleByEmail(email);
        if(user == null) {
            throw new UserNotFoundException();
        }
        final UserDTO userDTO = dtoUtils.userToUserDTO(user);
        return userDTO;
    }
    private void registerUserValidate(UserDTO userDTO){
        if(userDTO == null){
            throw new NotValidRequestException();
        }
        if(userDTO.getEmail() == null || userDTO.getPassword() == null){
            throw new NotValidRequestException();
        }
    }
    @Override
    public String registerUser(UserDTO userDTO) {
        registerUserValidate(userDTO);
        final User user = userRepository.getUserByEmail(userDTO.getEmail());
        if(user != null){
            throw new UserAlreadyExistException();
        }
        final User userReg = dtoUtils.userDTOToUser(userDTO);
        Role role = roleRepository.getUserRole();
        if(role == null){
            Role role1 = Role.builder().name("ROLE_USER").build();
            roleRepository.save(role1);
            role = role1;
        }
        userReg.setRoles(Collections.singleton(role));
        userRepository.save(userReg);
        return "User register";
    }
}
