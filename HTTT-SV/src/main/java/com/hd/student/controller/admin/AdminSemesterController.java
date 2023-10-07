package com.hd.student.controller.admin;

import com.hd.student.payload.request.SemesterDetailRequest;
import com.hd.student.payload.request.SemesterRequest;
import com.hd.student.payload.request.SemesterUserRequest;
import com.hd.student.service.SemesterDetailService;

import com.hd.student.service.SemesterService;
import com.hd.student.service.SemesterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/admin/")
public class AdminSemesterController {

    @Autowired
    private SemesterUserService semesterUserService;

    @Autowired
    private SemesterDetailService semesterDetailService;
    @Autowired
    private SemesterService semesterService;


    @GetMapping("/semester/getall")
    public ResponseEntity<?> getSemester() {
        return ResponseEntity.ok().body(this.semesterService.getAll());
    }

    @GetMapping("/semester/getavailable")
    public ResponseEntity<?> getAvailableSemester(){
        return ResponseEntity.ok().body(this.semesterService.getAvailable());
    }
    @GetMapping("/semester/get/{id}")
    public ResponseEntity<?> getAvailableSemester(@PathVariable int id){
        return ResponseEntity.ok().body(this.semesterService.getSemesterById(id));
    }

    @PutMapping("/semester/setFinish/{id}")
    public ResponseEntity<?> setFinish(@PathVariable int id){
        return ResponseEntity.ok().body(this.semesterService.setFinish(id));
    }

    @PostMapping("/semester/add")
    public ResponseEntity<?> addNewSemester(@RequestBody SemesterRequest rq){
        return ResponseEntity.ok().body(this.semesterService.addSemester(rq));
    }

    @PutMapping("/semester/update/{id}")
    public ResponseEntity<?> updateSemester(@RequestBody SemesterRequest rq, @PathVariable int id){
        return ResponseEntity.ok().body(this.semesterService.updateSemester(rq, id));
    }

    @DeleteMapping("/semester/delete/{id}")
    public ResponseEntity<?> deleteSemesterById(@PathVariable int id){
        return ResponseEntity.ok().body(this.semesterService.deleteSemesterById(id));
    }
    @PutMapping("/semester-user")
    public ResponseEntity<?> addSemesterUser(@RequestBody SemesterUserRequest rq){
        return ResponseEntity.ok().body(
                this.semesterUserService.addSemesterForUser(rq)
        );
    }

    //Tam thoi chua co chuc nang dang ky mon hoc
    @PostMapping("/semester-detail")
    public ResponseEntity<?> addSemesterDetail(@RequestBody SemesterDetailRequest rq){
        return ResponseEntity.ok().body(
                this.semesterDetailService.addNewCourseInSemesterDetails(rq)
        );
    }
}
