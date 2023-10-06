package com.hd.student.controller.user;

import com.hd.student.entity.User;
import com.hd.student.payload.response.SemesterDetailsResponse;
import com.hd.student.payload.response.SemesterUserResponse;
import com.hd.student.payload.response.UserInfoResponse;
import com.hd.student.repository.UserRepository;
import com.hd.student.security.JwtUtils;
import com.hd.student.security.UserPrincipal;
import com.hd.student.service.SemesterDetailService;
import com.hd.student.service.SemesterUserService;
import com.hd.student.service.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userDetailsService;

    @Autowired
    private SemesterUserService semesterUserService;

    @Autowired
    private SemesterDetailService semesterDetailService;

    @Autowired
    private UserRepository userRepository;

    private final Logger log = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/info")
    public ResponseEntity<?> getInfo(Authentication authentication){
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserPrincipal u = (UserPrincipal) authentication.getPrincipal();
        UserInfoResponse response = userDetailsService.getCurrentUserInfo(u.getUsername());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //Lay thong tin hoc ky theo user dang nhap
    //Cho biet thong tin hoc ky: bi khoa, khong bi khoa
    @GetMapping("/semester")
    public ResponseEntity<?> getSemester(Authentication auth){
        UserPrincipal u = (UserPrincipal) auth.getPrincipal();
        List<SemesterUserResponse> smt = this.semesterUserService.getSemestersByUserId(u.getId());
        return new ResponseEntity<>(smt, HttpStatus.OK);
    }

    //Thoi khoa bieu tuan cua hoc sinh
    @GetMapping("/semester/{id}/course")
    public ResponseEntity<?> getCourseBySemesterAndUser(Authentication auth, @PathVariable int id){
        UserPrincipal u =(UserPrincipal) auth.getPrincipal();
        List<SemesterDetailsResponse> rp = this.semesterDetailService.getDetails(id);
        return new ResponseEntity<>(rp, HttpStatus.OK);
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
