package com.hd.student.controller;

import com.hd.student.payload.request.SemesterDetailRequest;
import com.hd.student.payload.request.SemesterRequest;
import com.hd.student.payload.request.SemesterUserRequest;
import com.hd.student.service.SemesterDetailService;
import com.hd.student.service.SemesterService;
import com.hd.student.service.SemesterUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@Tag(name = "13. Semester", description = "Quản lý học kỳ - đang phát triển")
@RequestMapping("/api/semester")
public class SemesterController {
    @Autowired
    private SemesterService semesterService;
    @Autowired
    private SemesterUserService semesterUserService;
    @Autowired
    private SemesterDetailService semesterDetailService;


    @Operation(
            summary = "Get All Semester",
            description = "Lấy tất cả học kỳ"
    )
    @GetMapping("")
    public ResponseEntity<?> getSemester() {
        return ResponseEntity.ok().body(this.semesterService.getAll());
    }

    @Operation(
            summary = "Get Available Semester",
            description = "Lấy học kỳ đang khả dụng"
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/available")
    public ResponseEntity<?> getAvailableSemester(){
        return ResponseEntity.ok().body(this.semesterService.getAvailable());
    }

    @Operation(
            summary = "Get Semester By Id",
            description = "Lấy học kỳ theo Id"
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getAvailableSemester(@PathVariable int id){
        return ResponseEntity.ok().body(this.semesterService.getSemesterById(id));
    }

    @Operation(
            summary = "Set Semester Finish",
            description = "Kết thúc học kỳ"
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{id}/setFinish")
    public ResponseEntity<?> setFinish(@PathVariable int id){
        return ResponseEntity.ok().body(this.semesterService.setFinish(id));
    }

    @Operation(
            summary = "Add Semester",
            description = "Thêm học kỳ mới"
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("")
    public ResponseEntity<?> addNewSemester(@Valid @RequestBody SemesterRequest rq){
        return ResponseEntity.ok().body(this.semesterService.addSemester(rq));
    }

    @Operation(
            summary = "Update Semester",
            description = "Chỉnh sửa học kỳ"
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateSemester(@Valid @RequestBody SemesterRequest rq, @PathVariable int id){
        return ResponseEntity.ok().body(this.semesterService.updateSemester(rq, id));
    }

    @Operation(
            summary = "Delete Semester",
            description = "Xóa học kỳ"
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSemesterById(@PathVariable int id){
        return ResponseEntity.ok().body(this.semesterService.deleteSemesterById(id));
    }

    //Them user hoc trong semester
    //2 chuc nang chua phat trien: Dang ky mon hoc va them user vao hoc ky
    @Operation(
            summary = "Add User into Semester",
            description = "Thêm sinh viên vào học kỳ - chưa phát triển"
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/semester-user")
    public ResponseEntity<?> addSemesterUser(@Valid @RequestBody SemesterUserRequest rq){
        return ResponseEntity.ok().body(
                this.semesterUserService.addSemesterForUser(rq)
        );
    }

    @Operation(
            summary = "Add Course into SemesterUser",
            description = "Thêm môn học vào học kỳ của sinh viên (Đăng ký môn)- chưa phát triển"
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/semester-detail")
    public ResponseEntity<?> addSemesterDetail(@Valid @RequestBody SemesterDetailRequest rq){
        return ResponseEntity.ok().body(
                this.semesterDetailService.addNewCourseInSemesterDetails(rq)
        );
    }

}
