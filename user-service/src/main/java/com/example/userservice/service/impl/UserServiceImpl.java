package com.example.userservice.service.impl;

import com.example.userservice.dto.UserDto;
import com.example.userservice.exception.NotValidRequestException;
import com.example.userservice.exception.UserAlreadyExistException;
import com.example.userservice.exception.UserNotFoundException;
import com.example.userservice.model.Role;
import com.example.userservice.model.User;
import com.example.userservice.repository.RoleRepository;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.service.AccountService;
import com.example.userservice.service.UserService;
import com.example.userservice.utils.DtoUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final AccountService accountService;
    private final DtoUtils dtoUtils;
    @Override
    public UserDto getUserAndRoleByEmail(String email) {
        if(email == null){
            throw new NotValidRequestException();
        }
        final User user = userRepository.getUserAndRoleByEmail(email);
        if(user == null) {
            throw new UserNotFoundException();
        }
        final UserDto userDTO = dtoUtils.userToUserDTO(user);
        return userDTO;
    }
    private void registerUserValidate(UserDto userDto){
        if(userDto == null){
            throw new NotValidRequestException();
        }
        if(userDto.getEmail() == null || userDto.getPassword() == null){
            throw new NotValidRequestException();
        }
    }
    @Override
    public String registerUser(UserDto userDto) {
        registerUserValidate(userDto);
        final User user = userRepository.getUserByEmail(userDto.getEmail());
        if(user != null){
            throw new UserAlreadyExistException();
        }
        final User userReg = dtoUtils.userDTOToUser(userDto);
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
    public UserDto[] getNotAcceptedUser() {
        final List<User> users = userRepository.getNotAcceptedUser();
        if(users != null && !users.isEmpty()){
            final UserDto[] usersDto = new UserDto[users.size()];
            int index = 0;
            for(User u : users){
                usersDto[index] = dtoUtils.userToUserDTOWithoutRelationEntity(u);
                index++;
            }
            return usersDto;
        }
        return new UserDto[0];
    }
    private void acceptedUserByIdValid(UserDto userDto){
        if(userDto == null){
            throw new NotValidRequestException();
        }
        if(userDto.getId() == null){
            throw new NotValidRequestException();
        }
    }
    @Override
    public String acceptedUserById(UserDto userDto) {
        acceptedUserByIdValid(userDto);
        final Long user_id = Long.parseLong(userDto.getId());
        userRepository.acceptedUserById(user_id);
        accountService.createAccountWithUser(user_id);
        return "accepted";
    }
    private void bannedUserByIdValid(UserDto userDto){
        if(userDto == null){
            throw new NotValidRequestException();
        }
        if(userDto.getId() == null){
            throw new NotValidRequestException();
        }
    }
    @Override
    public String bannedUserById(UserDto userDto) {
        bannedUserByIdValid(userDto);
        userRepository.bannedUserById(Long.parseLong(userDto.getId()));
        return "banned";
    }

    @Override
    public UserDto[] getBannedUser() {
        final List<User> users = userRepository.getBannedUser();
        if(users != null && !users.isEmpty()){
            final UserDto[] usersDto = new UserDto[users.size()];
            int index = 0;
            for(User u : users){
                usersDto[index] = dtoUtils.userToUserDTOWithoutRelationEntity(u);
                index++;
            }
            return usersDto;
        }
        return new UserDto[0];
    }
    private void removeBannedUserByIdValid(UserDto userDto){
        if(userDto == null){
            throw new NotValidRequestException();
        }
        if(userDto.getId() == null){
            throw new NotValidRequestException();
        }
    }
    @Override
    public String removeBannedUser(UserDto userDto) {
        removeBannedUserByIdValid(userDto);
        userRepository.removeBannedUserById(Long.parseLong(userDto.getId()));
        return "remove banned";
    }

    @Override
    public UserDto getUserAccountByEmail(UserDto userDto) {
        final User user = userRepository.getUserByEmail(userDto.getEmail());
        final UserDto response = dtoUtils.userToUserDTOWithoutRelationEntity(user);
        return response;
    }
}
