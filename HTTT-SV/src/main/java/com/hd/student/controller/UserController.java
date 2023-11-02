package com.hd.student.controller;

import com.hd.student.payload.response.OnlineServiceResponse;
import com.hd.student.payload.response.SemesterDetailsResponse;
import com.hd.student.payload.response.SemesterUserResponse;
import com.hd.student.payload.response.UserInfoResponse;
import com.hd.student.security.JwtUtils;
import com.hd.student.security.UserPrincipal;
import com.hd.student.service.IOnlineService;
import com.hd.student.service.SemesterDetailService;
import com.hd.student.service.SemesterUserService;
import com.hd.student.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@Tag(name = "02. User", description = "Quản lý người dùng")
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
    private UserService userService;

    @Autowired
    private IOnlineService onlineService;


    @Operation(
            summary = "User Info",
            description = "Lấy thông tin người dùng đang đăng nhập"
    )
    @GetMapping("/info")
    public ResponseEntity<?> getInfo(Authentication authentication){
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserPrincipal u = (UserPrincipal) authentication.getPrincipal();

        UserInfoResponse response = userDetailsService.getCurrentUserInfo(u.getUsername());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(
            summary = "User Semester",
            description = "Lấy thông tin các học kỳ của người dùng đang đăng nhập"
    )
    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/my-semester")
    public ResponseEntity<?> getSemester(Authentication auth){
        UserPrincipal u = (UserPrincipal) auth.getPrincipal();
        return new ResponseEntity<>(this.semesterUserService.getSemestersByUserId(u.getId()), HttpStatus.OK);
    }

    @Operation(
            summary = "User Detail Semester",
            description = "Môn học trong học kỳ của người dùng đang đăng nhập"
    )
    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/my-semester/{id}/course")
    public ResponseEntity<?> getCourseBySemesterAndUser(Authentication auth, @PathVariable int id){
        UserPrincipal u =(UserPrincipal) auth.getPrincipal();
        List<SemesterDetailsResponse> rp = this.semesterDetailService.getDetails(id);
        return new ResponseEntity<>(rp, HttpStatus.OK);
    }

    @Operation(
            summary = "Get My OnlineService",
            description = "Lấy yêu cầu của người dùng đăng nhập"
    )
    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/my-request")
    public ResponseEntity<?> getMyOnlineService(Authentication auth){
        UserPrincipal u = (UserPrincipal) auth.getPrincipal();
        List<OnlineServiceResponse> onlineServiceResponses = onlineService.findAllByUserId(u.getId());
        return new ResponseEntity<>(onlineServiceResponses,HttpStatus.OK);
    }
    @Operation(
            summary = "Get All User",
            description = "Lấy thông tin của tất cả người dùng"
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("")
    public ResponseEntity<?> getAllUser(){
        List<UserInfoResponse> rp = this.userService.findAll(0);
        return new ResponseEntity<>(rp, HttpStatus.OK);
    }

//    @Operation(
//            summary = "Get Users By MajorId",
//            description = "Lấy thông tin của tất cả người dùng theo ngành"
//    )
//    @PreAuthorize("hasAuthority('ADMIN')")
//    @GetMapping("/major/{majorId}")
//    public ResponseEntity<?> getAllUserByMajor(@PathVariable(required = false) int majorId){
//        List<UserInfoResponse> rp = this.userService.findAll(majorId);
//        return new ResponseEntity<>(rp, HttpStatus.OK);
//    }
}
