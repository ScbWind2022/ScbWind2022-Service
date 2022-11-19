package com.example.userservice.utils;

import com.example.userservice.dto.CheckDto;
import com.example.userservice.dto.RoleDTO;
import com.example.userservice.dto.UserDTO;
import com.example.userservice.model.Check;
import com.example.userservice.model.Role;
import com.example.userservice.model.User;
import org.springframework.stereotype.Service;


@Service
public class DtoUtils {
    public UserDTO userToUserDTO(final User user){
        final RoleDTO[] roles = new RoleDTO[user.getRoles().size()];
        int index = 0;
        for(Role r : user.getRoles()){
            roles[index] = roleToRoleDTO(r);
        }
        final UserDTO userDTO = UserDTO.builder()
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
    public UserDTO userToUserDTOWithoutRelationEntity(final User user){
        final UserDTO userDTO = UserDTO.builder()
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
    public RoleDTO roleToRoleDTO(final Role role){
        final RoleDTO roleDTO = RoleDTO.builder()
                .name(role.getName())
                .build();
        return roleDTO;
    }
    public User userDTOToUser(final UserDTO userDTO){
        final User user = User.builder()
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .phone(userDTO.getPhone())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .build();
        return user;
    }
    public CheckDto checkToCheckDto(final Check check){
        final CheckDto checkDto = CheckDto.builder()
                .id(Integer.parseInt(String.valueOf(check.getId())))
                .currencyId(check.getCurrencyId())
                .currencyName(check.getCurrencyName())
                .currencyCharCode(check.getCurrencyCharCode())
                .sum(String.valueOf(check.getCount()))
                .enable(check.isEnabled())
                .build();
        return checkDto;
    }
    public Check checkDtoToCheck(final CheckDto checkDto){
        final Check check = Check.builder()
                .currencyCharCode(checkDto.getCurrencyCharCode())
                .build();
        return check;
    }
}
