package com.example.authservice.controller;

import com.example.authservice.dto.AccountEnableRequest;
import com.example.authservice.dto.AccountResponse;
import com.example.authservice.dto.AuthRequestDto;
import com.example.authservice.dto.CreateUserRequest;
import com.example.authservice.dto.UserProfileResponse;
import com.example.authservice.dto.UserResponse;
import com.example.authservice.dto.domestic.JwtDto;
import com.example.authservice.exception.IncorrectJwtTokenException;
import com.example.authservice.service.AccountService;
import com.example.authservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@RestController
@RequestMapping(value = "/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final AccountService checkService;

    @PostMapping(value = "/login")
    public ResponseEntity<JwtDto> login(@RequestBody AuthRequestDto userDTO, HttpServletResponse response) {
        System.out.println("3");
        final JwtDto jwtDTO = userService.loginUser(userDTO);
        Cookie cookie = new Cookie("refreshToken", jwtDTO.getRefreshToken());
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        jwtDTO.setRefreshToken(null);
        return new ResponseEntity<>(jwtDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<String> register(@RequestBody CreateUserRequest userDTO) {
        return new ResponseEntity<>(userService.registerUser(userDTO), HttpStatus.OK);
    }

    @PostMapping(value = "/admin")
    public ResponseEntity<String> admin(Principal principal) {
        System.out.println(principal.getName());
        return new ResponseEntity<>("admin", HttpStatus.OK);
    }

    @PostMapping(value = "/auth")
    public ResponseEntity<String> auth() {
        return new ResponseEntity<>("auth", HttpStatus.OK);
    }

    @PostMapping(value = "/update-token")
    public ResponseEntity<JwtDto> updateToken(HttpServletRequest request, HttpServletResponse response) {
        final Cookie[] cookies = request.getCookies();
        ;
        String refresh;
        for (Cookie c : cookies) {
            try {
                if (c.getName().equals("refreshToken")) {
                    refresh = c.getValue();
                    final JwtDto jwtDTO = userService.updateAccessAndRefreshToken(refresh);
                    System.out.println(c.getValue());
                    response.addCookie(new Cookie("refreshToken", jwtDTO.getRefreshToken()));
                    jwtDTO.setRefreshToken(null);
                    return new ResponseEntity<>(jwtDTO, HttpStatus.OK);
                }
            } catch (NullPointerException n) {

            }
        }
        throw new IncorrectJwtTokenException();
    }

    @PostMapping(value = "/profile/checks")
    public ResponseEntity<AccountResponse[]> getProfileAccountCheck(Principal principal) {
        return new ResponseEntity<>(checkService.getAccountByUserEmail(principal.getName()), HttpStatus.OK);
    }

    @PostMapping(value = "/profile/user")
    public ResponseEntity<UserResponse> getProfileAccountUser(Principal principal) {
        return new ResponseEntity<>(userService.getInfoUserByEmail(principal.getName()), HttpStatus.OK);
    }

    @PostMapping(value = "/check/enable")
    public ResponseEntity<String> enableCheckByEmailAndId(@RequestBody AccountEnableRequest request,
                                                          Principal principal) {
        return new ResponseEntity<>(checkService.changeEnabledByEmailAndId(request, principal.getName()),
                HttpStatus.OK);
    }

    @GetMapping(value = "/profile")
    public ResponseEntity<UserProfileResponse> getUserProfileResponse(Principal principal) {
        final UserProfileResponse userProfileResponse = UserProfileResponse.builder()
                .user(userService.getInfoUserByEmail(principal.getName()))
                .accounts(checkService.getAccountByUserEmail(principal.getName()))
                .build();
        return new ResponseEntity<>(userProfileResponse, HttpStatus.OK);
    }

    /*@PostMapping(value = "/getrefreshtoken")
    public ResponseEntity<JwtDTO> getAccessToken(@RequestBody String request){
        return new ResponseEntity<>(userService.getAccessAndRefreshToken(request), HttpStatus.OK);
    }*/

}
