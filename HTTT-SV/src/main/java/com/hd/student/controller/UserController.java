package com.hd.student.controller;

import com.hd.student.payload.request.LoginRequest;
import com.hd.student.payload.response.MessageResponse;
import com.hd.student.entity.User;
import com.hd.student.repository.UserRepository;
import com.hd.student.payload.response.UserInfoResponse;
import com.hd.student.security.UserPrincipal;
import com.hd.student.service.UserService;
import com.hd.student.security.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000/")
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

    @Autowired
    private UserRepository userRepository;

    private final Logger log = LoggerFactory.getLogger(UserController.class);

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

    @GetMapping("/all_users")
    List<User> getAllUsers() { return userRepository.findAll(); }

    @GetMapping("/get/{id}")
    ResponseEntity<?> getUser(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/new")
    ResponseEntity<User> createUser(@Valid @RequestBody User user) throws URISyntaxException {
        log.info("Request to create user: {}", user);
        User u = userRepository.save(user);
        return ResponseEntity.created(new URI("api/v1/user" + u.getId())).body((u));
    }

    @PutMapping("/update/{id}")
    ResponseEntity<User> updateUser(@Valid @RequestBody User user) {
        log.info("Request to update user: {}", user);
        User u = userRepository.save(user);
        return ResponseEntity.ok().body(u);
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<User> deleteUser(@PathVariable int id) {
        log.info("Request to delete user: {}", id);
        userRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
