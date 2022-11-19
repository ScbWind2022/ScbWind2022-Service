package com.example.userservice.service.impl;

import com.example.userservice.dto.CheckDto;
import com.example.userservice.dto.UserDTO;
import com.example.userservice.exception.NotValidRequestException;
import com.example.userservice.exception.UserAlreadyExistException;
import com.example.userservice.exception.UserNotFoundException;
import com.example.userservice.model.Check;
import com.example.userservice.model.Role;
import com.example.userservice.model.User;
import com.example.userservice.repository.RoleRepository;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.service.CheckService;
import com.example.userservice.service.UserService;
import com.example.userservice.utils.DtoUtils;
import lombok.RequiredArgsConstructor;
import org.checkerframework.checker.units.qual.C;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final CheckService checkService;
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

    @Override
    public UserDTO[] getNotAcceptedUser() {
        final List<User> users = userRepository.getNotAcceptedUser();
        if(users != null && users.size() > 0){
            final UserDTO[] usersDto = new UserDTO[users.size()];
            int index = 0;
            for(User u : users){
                usersDto[index] = dtoUtils.userToUserDTOWithoutRelationEntity(u);
                index++;
            }
            return usersDto;
        }
        return new UserDTO[0];
    }
    private void acceptedUserByIdValid(UserDTO userDTO){
        if(userDTO == null){
            throw new NotValidRequestException();
        }
        if(userDTO.getId() == null){
            throw new NotValidRequestException();
        }
    }
    @Override
    public String acceptedUserById(UserDTO userDTO) {
        acceptedUserByIdValid(userDTO);
        final Long user_id = Long.parseLong(userDTO.getId());
        userRepository.acceptedUserById(user_id);
        checkService.createCheckWithUser(user_id);
        return "accepted";
    }
    private void bannedUserByIdValid(UserDTO userDTO){
        if(userDTO == null){
            throw new NotValidRequestException();
        }
        if(userDTO.getId() == null){
            throw new NotValidRequestException();
        }
    }
    @Override
    public String bannedUserById(UserDTO userDTO) {
        bannedUserByIdValid(userDTO);
        userRepository.bannedUserById(Long.parseLong(userDTO.getId()));
        return "banned";
    }

    @Override
    public UserDTO[] getBannedUser() {
        final List<User> users = userRepository.getBannedUser();
        if(users != null && users.size() > 0){
            final UserDTO[] usersDto = new UserDTO[users.size()];
            int index = 0;
            for(User u : users){
                usersDto[index] = dtoUtils.userToUserDTOWithoutRelationEntity(u);
                index++;
            }
            return usersDto;
        }
        return new UserDTO[0];
    }
    private void removeBannedUserByIdValid(UserDTO userDTO){
        if(userDTO == null){
            throw new NotValidRequestException();
        }
        if(userDTO.getId() == null){
            throw new NotValidRequestException();
        }
    }
    @Override
    public String removeBannedUser(UserDTO userDTO) {
        removeBannedUserByIdValid(userDTO);
        userRepository.removeBannedUserById(Long.parseLong(userDTO.getId()));
        return "remove banned";
    }

    @Override
    public UserDTO getUserAccountByEmail(UserDTO userDTO) {
        final User user = userRepository.getUserByEmail(userDTO.getEmail());
        final UserDTO userDTO1 = dtoUtils.userToUserDTOWithoutRelationEntity(user);
        return userDTO1;
    }
}
