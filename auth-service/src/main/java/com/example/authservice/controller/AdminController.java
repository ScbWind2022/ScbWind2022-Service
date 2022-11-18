package com.example.authservice.controller;

import com.example.authservice.dto.UserDTO;
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
@RequestMapping(value = "/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;
    private final AdminService adminService;
    @PostMapping(value = "/get/not-accepted-user")
    public ResponseEntity<UserDTO[]> getNotAcceptedUser(){
        return new ResponseEntity<>(adminService.getNotAcceptedUsers(), HttpStatus.OK);
    }
    @PostMapping(value = "/get/banned-user")
    public ResponseEntity<UserDTO[]> getBannedUser(){
        return null;
    }
    @PostMapping(value = "/accept-user")
    public ResponseEntity<String> acceptedUser(@RequestBody UserDTO userDTO){
       return new ResponseEntity<>(adminService.acceptedUser(userDTO),HttpStatus.OK);
    }
    @PostMapping(value = "/user-banned")
    public ResponseEntity<String> bannedUser(@RequestBody UserDTO userDTO){
        return new ResponseEntity<>(adminService.bannedUser(userDTO),HttpStatus.OK);
    }
    @PostMapping(value = "/user-removebanned")
    public ResponseEntity<String> removeBannedUser(@RequestBody UserDTO userDTO){
        return null;
    }
}
