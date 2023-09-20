package com.hd.student.controller;

import com.hd.student.entity.User;
import com.hd.student.entity.UserInfo;
import com.hd.student.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserServiceImpl UserDetailsService;

    @PostMapping("/new")
    public ResponseEntity<?> addUser(@RequestBody User user) {
        UserDetailsService.addUser(user);
        return ResponseEntity.ok("User registered successfully");
    }
}
