package com.hd.student.controller;

import com.hd.student.payload.response.DepartmentResponse;
import com.hd.student.service.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@Tag(name = "14. Department", description = "Quản lý Khoa - Chưa phát triển")
@RequestMapping("/api/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @Operation(
            summary = "Get All Department",
            description = "Lấy tất cả chi tiết Khoa"
    )
    @GetMapping("")
    public ResponseEntity<?> getAllDepartment(){
        List<DepartmentResponse> rp = this.departmentService.findAllDepartment();
        return new ResponseEntity<>(rp, HttpStatus.OK);
    }

    @Operation(
            summary = "Get Department By Id",
            description = "Lấy Khoa theo Id"
    )
    @GetMapping("/{id}")
    public ResponseEntity<?> getAllDepartmentById(@PathVariable int id){
        return new ResponseEntity<>(this.departmentService.findDepartmentById(id), HttpStatus.OK);
    }
}
