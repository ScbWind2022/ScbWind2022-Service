package com.example.authservice.controller;

import com.example.authservice.dto.*;
import com.example.authservice.dto.maindto.JwtDTO;
import com.example.authservice.dto.maindto.StatusDto;
import com.example.authservice.exception.IncorrectJwtTokenException;
import com.example.authservice.service.CheckService;
import com.example.authservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@RestController
@RequestMapping(value = "/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final CheckService checkService;

    @PostMapping(value = "/login")
    public ResponseEntity<JwtDTO> login(@RequestBody AuthRequestDto userDTO, HttpServletResponse response){
        System.out.println("3");
        final JwtDTO jwtDTO = userService.loginUser(userDTO);
        Cookie cookie = new Cookie("refreshToken", jwtDTO.getRefreshToken());
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        jwtDTO.setRefreshToken(null);
        return new  ResponseEntity<>(jwtDTO, HttpStatus.OK);
    }
    @PostMapping(value = "/register")
    public ResponseEntity<String> register(@RequestBody CreateUserRequest userDTO){
        return new ResponseEntity<>(userService.registerUser(userDTO),HttpStatus.OK);
    }
    @PostMapping(value = "/admin")
    public ResponseEntity<String> admin(Principal principal){
        System.out.println(principal.getName());
        return new ResponseEntity<>("admin",HttpStatus.OK);
    }
    @PostMapping(value = "/auth")
    public ResponseEntity<String> auth(){
        return new ResponseEntity<>("auth",HttpStatus.OK);
    }
    @PostMapping(value = "/update-token")
    public ResponseEntity<JwtDTO> updateToken(HttpServletRequest request, HttpServletResponse response){
        final Cookie[] cookies = request.getCookies();;
        String refresh;
        for(Cookie c : cookies){
            try {
                if(c.getName().equals("refreshToken")){
                    refresh = c.getValue();
                    final JwtDTO jwtDTO = userService.updateAccessAndRefreshToken(refresh);
                    System.out.println(c.getValue());
                    response.addCookie(new Cookie("refreshToken",jwtDTO.getRefreshToken()));
                    jwtDTO.setRefreshToken(null);
                    return new ResponseEntity<>(jwtDTO,HttpStatus.OK);
                }
            } catch (NullPointerException n){

            }
        }
        throw new IncorrectJwtTokenException();
    }
    @PostMapping(value = "/profile/checks")
    public ResponseEntity<AccountResponseDto[]> getProfileAccountCheck(Principal principal){
        return new ResponseEntity<>(checkService.getCheckWithUserByEmail(principal.getName()),HttpStatus.OK);
    }
    @PostMapping(value = "/profile/user")
    public ResponseEntity<UserResponse> getProfileAccountUser(Principal principal){
        return new ResponseEntity<>(userService.getInfoUserByEmail(principal.getName()),HttpStatus.OK);
    }
    @PostMapping(value = "/check/enable")
    public ResponseEntity<String> enableCheckByEmailAndId(@RequestBody UserEnableRequest userEnableRequest,
                                                             Principal principal){
        return new ResponseEntity<>(checkService.changeEnabledByEmailAndId(
                userEnableRequest, principal.getName()),HttpStatus.OK);
    }
    @GetMapping(value = "/profile")
    public ResponseEntity<UserProfileResponse> getUserProfileResponse(Principal principal){
        final UserProfileResponse userProfileResponse = UserProfileResponse.builder()
                .user(userService.getInfoUserByEmail(principal.getName()))
                .accounts(checkService.getCheckWithUserByEmail(principal.getName()))
                .build();
        return new ResponseEntity<>(userProfileResponse,HttpStatus.OK);
    }

    /*@PostMapping(value = "/getrefreshtoken")
    public ResponseEntity<JwtDTO> getAccessToken(@RequestBody String request){
        return new ResponseEntity<>(userService.getAccessAndRefreshToken(request), HttpStatus.OK);
    }*/

}
