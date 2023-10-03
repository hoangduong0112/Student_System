package com.hd.student.controller;

import com.hd.student.service.CourseDataService;
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
    private CourseDataService courseDataService;

    @GetMapping("/course")
    public ResponseEntity<?> getAllCourseData(){
        return new ResponseEntity<>(this.courseDataService.getAll(null), HttpStatus.OK);
    }
}
