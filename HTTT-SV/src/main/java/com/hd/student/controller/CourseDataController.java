package com.hd.student.controller;

import com.hd.student.payload.request.CourseDataRequest;
import com.hd.student.service.CourseDataService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@Tag(name = "12. CourseData", description = "Quản lý chi tiết môn học")
@RequestMapping("/api/course-data")
public class CourseDataController {
    @Autowired
    private CourseDataService courseDataService;

    @Operation(
            summary = "Get All CourseData",
            description = "Lấy tất cả chi tiết môn học"
    )
    @GetMapping("")
    public ResponseEntity<?> getAllCourse(@RequestParam(required = false) String search){
        return new ResponseEntity<>(this.courseDataService.getAll(search), HttpStatus.OK);
    }

    @Operation(
            summary = "Get CourseData By Id",
            description = "Lấy chi tiết môn học by Id"
    )
    @GetMapping("/{id}")
    public ResponseEntity<?> getCourseById(@PathVariable int id){
        return new ResponseEntity<>(this.courseDataService.getCourseDataById(id), HttpStatus.OK);
    }

    @Operation(
            summary = "Add CourseData",
            description = "Thêm chi tiết môn học mới"
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("")
    public ResponseEntity<?> addCourseData(@Valid @RequestBody CourseDataRequest rq){
        return new ResponseEntity<>(this.courseDataService.addNewCourseData(rq), HttpStatus.OK);
    }

    @Operation(
            summary = "Update CourseData",
            description = "Chỉnh sửa chi tiết môn học"
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping ("/{id}")
    public ResponseEntity<?> updateCourseData(@Valid @RequestBody CourseDataRequest rq, @PathVariable int id){
        return new ResponseEntity<>(this.courseDataService.updateCourseData(rq, id), HttpStatus.OK);
    }

    @Operation(
            summary = "Delete CourseData By Id",
            description = "Xóa chi tiết môn học"
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCourseData(@PathVariable int id){
        return new ResponseEntity<>(this.courseDataService.deleteCourseData(id), HttpStatus.OK);
    }
}
