package com.hd.student.controller;

import com.hd.student.payload.request.CourseDatumRequest;
import com.hd.student.service.CourseDatumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

<<<<<<< HEAD:HTTT-SV/src/main/java/com/hd/student/controller/admin/AdminCourseDataController.java
@CrossOrigin
@RestController
@RequestMapping("/api/admin/")
public class AdminCourseDataController {
=======
@RequestMapping("/api/v1/course-data")
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ScheduleInfoController {
>>>>>>> parent of 45da031 (forked):HTTT-SV/src/main/java/com/hd/student/controller/ScheduleInfoController.java

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

<<<<<<< HEAD:HTTT-SV/src/main/java/com/hd/student/controller/admin/AdminCourseDataController.java
    @PutMapping ("/course-data/update/{id}")
    public ResponseEntity<?> updateCourseData(@RequestBody CourseDatumRequest rq,@PathVariable int id){
        return new ResponseEntity<>(this.courseDatumService.updateCourseData(rq, id), HttpStatus.OK);
=======
    @PostMapping("/update/{id}")
    public ResponseEntity<?> updateCourseData(@RequestBody CourseDataRequest rq, @PathVariable int id){
        return new ResponseEntity<>(this.courseDataService.updateCourseData(rq, id), HttpStatus.OK);
>>>>>>> parent of 45da031 (forked):HTTT-SV/src/main/java/com/hd/student/controller/ScheduleInfoController.java
    }
}
