package com.hd.student.controller;

import com.hd.student.payload.request.CourseDatumRequest;
import com.hd.student.payload.response.CourseDatumResponse;
import com.hd.student.service.CourseDatumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/")
@RestController
@CrossOrigin
public class ScheduleInfoController {

    @Autowired
    private CourseDatumService courseDatumService;

    @GetMapping("/course-data/getAll")
    public ResponseEntity<?> getAllCourse(@RequestParam(required = false) String search){
        return new ResponseEntity<>(this.courseDatumService.getAll(search), HttpStatus.OK);
    }

    @PostMapping("/course-data/add")
    public ResponseEntity<?> addCourseData(@RequestBody CourseDatumRequest rq){
        return new ResponseEntity<>(this.courseDatumService.addNewCourseData(rq), HttpStatus.OK);
    }

    @PostMapping("/course-data/update/{id}")
    public ResponseEntity<?> updateCourseData(@RequestBody CourseDatumRequest rq,@PathVariable int id){
        return new ResponseEntity<>(this.courseDatumService.updateCourseData(rq, id), HttpStatus.OK);
    }
}
