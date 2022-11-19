package com.example.authservice.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfileResponse {
    private UserResponse user;
    private AccountResponseDto[] accounts;
}
