package com.example.rateservice.dto.maindto;

import lombok.*;

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
    private String lastName;
    private String phone;
    private boolean accepted;
    private boolean banned;

    private String dateCreate;


}
