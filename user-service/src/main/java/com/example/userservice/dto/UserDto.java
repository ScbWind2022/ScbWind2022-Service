package com.example.userservice.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class UserDto {
    private String id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private boolean accepted;
    private boolean banned;
    private RoleDto[] roles;

    public UserDto(String email, RoleDto[] roles) {
        this.email = email;
        this.roles = roles;
    }
}
