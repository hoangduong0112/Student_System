package com.hd.student.controller;

import com.hd.student.payload.request.CourseRequest;
import com.hd.student.service.CourseService;
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
@Tag(name = "10. Course", description = "Quản lý môn học")
@RequestMapping("/api/course/")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @Operation(
            summary = "Get All course",
            description = "Lấy tất cả môn học"
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/getall")
    public ResponseEntity<?> getAllCourse(@RequestParam(required = false) String search){
        return new ResponseEntity<>(this.courseService.getAllCourse(search), HttpStatus.OK);
    }

    @Operation(
            summary = "Get course by Id",
            description = "Lấy môn học theo id"
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getCourseById(@PathVariable int id){
        return new ResponseEntity<>(this.courseService.getCourseById(id), HttpStatus.OK);
    }

    @Operation(
            summary = "Add Course",
            description = "Thêm 1 môn học mới"
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<?> addNewCourse(@Valid @RequestBody CourseRequest rq){
        return new ResponseEntity<>(this.courseService.addNewCourse(rq), HttpStatus.OK);
    }

    @Operation(
            summary = "Update Course",
            description = "Chỉnh sửa môn học hiện có"
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?>  updateCourse(@Valid @RequestBody CourseRequest rq, @PathVariable int id){
        return new ResponseEntity<>(this.courseService.updateCourse(rq, id), HttpStatus.OK);
    }

    @Operation(
            summary = "Delete Course",
            description = "Xóa môn học hiện có"
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping ("/delete/{id}")
    public ResponseEntity<?>  deleteCourse(@PathVariable int id){
        return new ResponseEntity<>(this.courseService.deleteCourse(id), HttpStatus.OK);
    }
}
