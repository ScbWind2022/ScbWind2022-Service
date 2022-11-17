package com.example.authservice.controller;

import com.example.authservice.dto.JwtDTO;
import com.example.authservice.dto.UserDTO;
import com.example.authservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping(value = "/api/v1/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<JwtDTO> login(@RequestBody UserDTO userDTO){
        System.out.println("Asdsad");
        return new  ResponseEntity<>(userService.loginUser(userDTO), HttpStatus.OK);
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
    /*@PostMapping(value = "/getrefreshtoken")
    public ResponseEntity<JwtDTO> getAccessToken(@RequestBody String request){
        return new ResponseEntity<>(userService.getAccessAndRefreshToken(request), HttpStatus.OK);
    }*/

}
