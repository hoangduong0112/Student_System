package com.hd.student.controller;

import com.hd.student.payload.response.SemesterUserResponse;
import com.hd.student.security.UserPrincipal;
import com.hd.student.service.SemesterDetailService;
import com.hd.student.service.SemesterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class ScheduleController {

    @Autowired
    private SemesterUserService semesterUserService;

    @Autowired
    private SemesterDetailService semesterDetailService;

//    @GetMapping("/user/semester")
//    public ResponseEntity<?> getSemester(Authentication auth){
//        UserPrincipal u = (UserPrincipal) auth.getPrincipal();
//        List<SemesterUserResponse> smt = this.semesterUserService.getSemestersByUserId(u.getId());
//        return new ResponseEntity<>(smt, HttpStatus.OK);
//    }
}
