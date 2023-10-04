package com.hd.student.controller;

import com.hd.student.payload.request.CourseDataRequest;
import com.hd.student.service.CourseDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/course-data")
@RestController
@CrossOrigin
public class ScheduleInfoController {

    @Autowired
    private CourseDataService courseDataService;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllCourse(@RequestParam(required = false) String search){
        return new ResponseEntity<>(this.courseDataService.getAll(search), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addCourseData(@RequestBody CourseDataRequest rq){
        return new ResponseEntity<>(this.courseDataService.addNewCourseData(rq), HttpStatus.OK);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> updateCourseData(@RequestBody CourseDataRequest rq, @PathVariable int id){
        return new ResponseEntity<>(this.courseDataService.updateCourseData(rq, id), HttpStatus.OK);
    }
}
