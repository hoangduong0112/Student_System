package com.hd.student.controller.user;

import com.hd.student.payload.response.SemesterUserResponse;
import com.hd.student.security.UserPrincipal;
import com.hd.student.service.SemesterDetailService;
import com.hd.student.service.SemesterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/user/service")
public class SemesterController {
    @Autowired
    private SemesterUserService semesterUserService;

    @Autowired
    private SemesterDetailService semesterDetailService;


    @GetMapping("/user/semester")
    public ResponseEntity<?> getSemester(Authentication auth){
        UserPrincipal u = (UserPrincipal) auth.getPrincipal();
        List<SemesterUserResponse> smt = this.semesterUserService.getSemestersByUserId(u.getId());
        return new ResponseEntity<>(smt, HttpStatus.OK);
    }
}
