package com.hd.student.controller;

import com.hd.student.entity.User;
import com.hd.student.payload.request.LoginRequest;
import com.hd.student.payload.response.MessageResponse;
import com.hd.student.security.JwtUtils;
import com.hd.student.security.UserPrincipal;
import com.hd.student.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@Tag(name = "01. Authentication", description = "Xác thực người dùng")
@RequestMapping("/api/auth/")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserService userDetailsService;


    @Operation(
            summary = "User Sign up",
            description = "Đăng ký user - chưa phát triển hết vì trong hệ thống sinh viên không có đăng ký - phát triển import excel"
            )
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user) {
        this.userDetailsService.saveUser(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @Operation(
            summary = "User Sign out",
            description = "Đăng xuất user - xóa cookie"
    )
    @PostMapping(value = "/signout")
    public ResponseEntity<?> logoutUser() {
//        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
//        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
//                .body(new MessageResponse("You've been signed out!"));
        return ResponseEntity.ok()
                .body(new MessageResponse("You've been signed out!"));
    }

    @Operation(
            summary = "User Sign in",
            description = "Đăng nhập user bằng email và password"
    )
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest rq) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(rq.getEmail(), rq.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserPrincipal u = (UserPrincipal) authentication.getPrincipal();

        String jwtCookie = jwtUtils.generateJwtToken(authentication);


        return ResponseEntity.ok(jwtCookie);
    }
}
