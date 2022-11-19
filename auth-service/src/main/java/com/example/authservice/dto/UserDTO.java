package com.example.authservice.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class UserDTO {
    private String id;
    private String email;
    private String password;
    private String firstName;
    private boolean accepted;
    private boolean banned;
    private RoleDTO[] roles;

    private String dateCreate;

    public UserDTO(String email, RoleDTO[] roles) {
        this.email = email;
        this.roles = roles;
    }
}
