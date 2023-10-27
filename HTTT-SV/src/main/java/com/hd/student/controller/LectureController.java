package com.hd.student.controller;

import com.hd.student.entity.enums.Role;
import com.hd.student.payload.request.LectureRequest;
import com.hd.student.service.LectureService;
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
@Tag(name = "11. Lecture", description = "Quản lý giảng viên")
@RequestMapping("/api/lecture")
public class LectureController {
    @Autowired
    private LectureService lectureService;

    @Operation(
            summary = "Get All Lecture",
            description = "Lấy tất cả giảng viên"
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("")
    public ResponseEntity<?> getAll(){
        return new ResponseEntity<>(this.lectureService.getAll(), HttpStatus.OK);
    }

    @Operation(
            summary = "Get Lecture By Id",
            description = "Lấy giảng viên theo id"
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getLectureById(@PathVariable int id){
        return new ResponseEntity<>(this.lectureService.getById(id), HttpStatus.OK);
    }

    @Operation(
            summary = "Add Lecture",
            description = "Thêm giảng viên mới"
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("")
    public ResponseEntity<?> addNewLecture(@Valid @RequestBody LectureRequest rq){
        return new ResponseEntity<>(this.lectureService.addNewLecture(rq), HttpStatus.OK);
    }

    @Operation(
            summary = "Update Lecture",
            description = "Chỉnh sửa 1 giảng viên"
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?>  updateLecture(@Valid @RequestBody LectureRequest rq, @PathVariable int id){
        return new ResponseEntity<>(this.lectureService.updateLecture(rq, id), HttpStatus.OK);
    }

    @Operation(
            summary = "Delete Lecture By Id",
            description = "Xóa giảng viên by Id"
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping ("/{id}")
    public ResponseEntity<?>  deleteLecture(@PathVariable int id){
        return new ResponseEntity<>(this.lectureService.deleteLecture(id), HttpStatus.OK);
    }
}
