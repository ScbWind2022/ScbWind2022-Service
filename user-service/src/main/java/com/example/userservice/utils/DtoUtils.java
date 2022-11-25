package com.example.userservice.utils;

import com.example.userservice.dto.AccountDto;
import com.example.userservice.dto.RoleDto;
import com.example.userservice.dto.UserDto;
import com.example.userservice.model.Account;
import com.example.userservice.model.Role;
import com.example.userservice.model.User;
import org.springframework.stereotype.Service;


@Service
public class DtoUtils {
    public UserDto userToUserDTO(final User user) {
        final RoleDto[] roles = new RoleDto[user.getRoles().size()];
        int index = 0;
        for (Role r : user.getRoles()) {
            roles[index] = roleToRoleDTO(r);
        }
        final UserDto userDTO = UserDto.builder()
                .id(String.valueOf(user.getId()))
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .password(user.getPassword())
                .accepted(user.isAccepted())
                .banned(user.isBanned())
                .phone(user.getPhone())
                .roles(roles)
                .build();
        return userDTO;
    }

    public UserDto userToUserDTOWithoutRelationEntity(final User user) {
        final UserDto userDTO = UserDto.builder()
                .id(String.valueOf(user.getId()))
                .email(user.getEmail())
                .accepted(user.isAccepted())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phone(user.getPhone())
                .banned(user.isBanned())
                .build();
        return userDTO;
    }

    public RoleDto roleToRoleDTO(final Role role) {
        final RoleDto roleDTO = RoleDto.builder()
                .name(role.getName())
                .build();
        return roleDTO;
    }

    public User userDTOToUser(final UserDto userDTO) {
        final User user = User.builder()
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .phone(userDTO.getPhone())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .build();
        return user;
    }

    public AccountDto accountToAccountDto(final Account check) {
        final AccountDto checkDto = AccountDto.builder()
                .id(Integer.parseInt(String.valueOf(check.getId())))
                .userId(check.getUser().getId())
                .currencyId(check.getCurrencyId())
                .currencyName(check.getCurrencyName())
                .currencyCharCode(check.getCurrencyCharCode())
                .sum(check.getSum().toString())
                .enable(check.isEnabled())
                .build();
        return checkDto;
    }

    public Account checkDtoToCheck(final AccountDto checkDto) {
        final Account check = Account.builder()
                .currencyId(checkDto.getCurrencyId())
                .build();
        return check;
    }
}
