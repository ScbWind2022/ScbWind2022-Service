package com.example.userservice.utils;

import com.example.userservice.dto.RoleDTO;
import com.example.userservice.dto.UserDTO;
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
                .email(user.getEmail())
                .password(user.getPassword())
                .roles(roles)
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
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .build();
        return user;
    }
}
