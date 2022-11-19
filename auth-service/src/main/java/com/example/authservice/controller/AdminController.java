package com.example.authservice.controller;

import com.example.authservice.dto.maindto.UserDTO;
import com.example.authservice.dto.UserResponse;
import com.example.authservice.service.AdminService;
import com.example.authservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/users")
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;
    private final AdminService adminService;
    @PostMapping(value = "/get/enabled")
    public ResponseEntity<UserResponse[]> getNotAcceptedUser(){
        return new ResponseEntity<>(adminService.getNotAcceptedUsers(), HttpStatus.OK);
    }
    @PostMapping(value = "/get/banned")
    public ResponseEntity<UserResponse[]> getBannedUser(){
        return new ResponseEntity<>(adminService.getBannedUser(),HttpStatus.OK);
    }
    @PostMapping(value = "/enable")
    public ResponseEntity<String> acceptedUser(@RequestBody UserDTO userDTO){
       return new ResponseEntity<>(adminService.acceptedUser(userDTO),HttpStatus.OK);
    }
    @PostMapping(value = "/banned")
    public ResponseEntity<String> bannedUser(@RequestBody UserDTO userDTO){
        return new ResponseEntity<>(adminService.bannedUser(userDTO),HttpStatus.OK);
    }
    @PostMapping(value = "/removebanned")
    public ResponseEntity<String> removeBannedUser(@RequestBody UserDTO userDTO){
        return new ResponseEntity<>(adminService.removeBannedUser(userDTO),HttpStatus.OK);
    }
}
