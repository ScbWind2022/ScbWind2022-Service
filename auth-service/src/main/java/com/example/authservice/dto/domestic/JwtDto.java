package com.example.authservice.dto.domestic;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class JwtDto {
    private String accessToken;
    private String refreshToken;
}
