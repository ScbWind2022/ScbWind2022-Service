package com.example.authservice.controller;

import com.example.authservice.dto.JwtDTO;
import com.example.authservice.dto.UserDTO;
import com.example.authservice.exception.IncorrectJwtTokenException;
import com.example.authservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@RestController
@RequestMapping(value = "/api/v1/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<JwtDTO> login(@RequestBody UserDTO userDTO,HttpServletResponse response){
        System.out.println("3");
        final JwtDTO jwtDTO = userService.loginUser(userDTO);
        response.addCookie(new Cookie("refreshToken", jwtDTO.getRefreshToken()));
        jwtDTO.setRefreshToken(null);
        return new  ResponseEntity<>(jwtDTO, HttpStatus.OK);
    }
    @PostMapping(value = "/register")
    public ResponseEntity<String> register(@RequestBody UserDTO userDTO){
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
    /*@PostMapping(value = "/getrefreshtoken")
    public ResponseEntity<JwtDTO> getAccessToken(@RequestBody String request){
        return new ResponseEntity<>(userService.getAccessAndRefreshToken(request), HttpStatus.OK);
    }*/

}
