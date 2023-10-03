package com.hd.student.controller;

import com.hd.student.service.CourseDatumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1")
@RestController
@CrossOrigin
public class CourseDataController {

    @Autowired
    private CourseDatumService courseDatumService;

    @GetMapping("/course")
    public ResponseEntity<?> getAllCourseData(){
        return new ResponseEntity<>(this.courseDatumService.getAll(null), HttpStatus.OK);
    }
}
