package com.hd.student.controller;

import com.hd.student.payload.LoginRequest;
import com.hd.student.payload.MessageResponse;
import com.hd.student.entity.User;
import com.hd.student.payload.UserInfoResponse;
import com.hd.student.security.UserPrincipal;
import com.hd.student.service.UserService;
import com.hd.student.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserService userDetailsService;


    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        this.userDetailsService.saveUser(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
    @PostMapping(value = "/signout")
    public ResponseEntity<?> logoutUser() {
        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(new MessageResponse("You've been signed out!"));
    }


    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest rq) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(rq.getEmail(), rq.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserPrincipal u = (UserPrincipal) authentication.getPrincipal();

        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(u);

        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("email", u.getUsername());
        responseBody.put("role", u.getAuthorities().toString());

        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(responseBody);
    }
    @GetMapping("/info")
    public ResponseEntity<?> getInfo(Authentication authentication){
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserPrincipal u = (UserPrincipal) authentication.getPrincipal();
        UserInfoResponse response = userDetailsService.getCurrentUserInfo(u.getUsername());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
