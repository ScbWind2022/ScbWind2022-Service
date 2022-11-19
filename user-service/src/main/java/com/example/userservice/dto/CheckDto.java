package com.example.userservice.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class CheckDto {
    private String id;
    private String checkToken;
    private UserDTO owner;
}
