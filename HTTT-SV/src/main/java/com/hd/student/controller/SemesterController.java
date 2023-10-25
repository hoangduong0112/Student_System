package com.hd.student.controller;

import com.hd.student.payload.request.SemesterDetailRequest;
import com.hd.student.payload.request.SemesterRequest;
import com.hd.student.payload.request.SemesterUserRequest;
import com.hd.student.service.SemesterDetailService;
import com.hd.student.service.SemesterService;
import com.hd.student.service.SemesterUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/semester/")
public class SemesterController {
    @Autowired
    private SemesterService semesterService;
    @Autowired
    private SemesterUserService semesterUserService;
    @Autowired
    private SemesterDetailService semesterDetailService;


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/getall")
    public ResponseEntity<?> getSemester() {
        return ResponseEntity.ok().body(this.semesterService.getAll());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/getavailable")
    public ResponseEntity<?> getAvailableSemester(){
        return ResponseEntity.ok().body(this.semesterService.getAvailable());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getAvailableSemester(@PathVariable int id){
        return ResponseEntity.ok().body(this.semesterService.getSemesterById(id));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/setFinish/{id}")
    public ResponseEntity<?> setFinish(@PathVariable int id){
        return ResponseEntity.ok().body(this.semesterService.setFinish(id));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<?> addNewSemester(@Valid @RequestBody SemesterRequest rq){
        return ResponseEntity.ok().body(this.semesterService.addSemester(rq));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateSemester(@Valid @RequestBody SemesterRequest rq, @PathVariable int id){
        return ResponseEntity.ok().body(this.semesterService.updateSemester(rq, id));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/semester/delete/{id}")
    public ResponseEntity<?> deleteSemesterById(@PathVariable int id){
        return ResponseEntity.ok().body(this.semesterService.deleteSemesterById(id));
    }

    //Them user hoc trong semester
    //2 chuc nang chua phat trien: Dang ky mon hoc va them user vao hoc ky
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/semester-user")
    public ResponseEntity<?> addSemesterUser(@Valid @RequestBody SemesterUserRequest rq){
        return ResponseEntity.ok().body(
                this.semesterUserService.addSemesterForUser(rq)
        );
    }

    //Tam thoi chua co chuc nang dang ky mon hoc
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/semester-detail")
    public ResponseEntity<?> addSemesterDetail(@Valid @RequestBody SemesterDetailRequest rq){
        return ResponseEntity.ok().body(
                this.semesterDetailService.addNewCourseInSemesterDetails(rq)
        );
    }

}
