package com.example.authservice.dto.maindto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class JwtDTO {
    private String accessToken;
    private String refreshToken;
}
