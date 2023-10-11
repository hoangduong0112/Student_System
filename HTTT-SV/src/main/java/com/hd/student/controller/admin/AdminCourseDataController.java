package com.hd.student.controller.admin;

import com.hd.student.payload.request.CourseDatumRequest;
import com.hd.student.service.CourseDatumService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@Tag(name = "08. CourseData", description = "QL thong tin mon hoc")
@RequestMapping("/api/admin/")
public class AdminCourseDataController {

    @Autowired
    private CourseDatumService courseDatumService;

    @GetMapping("/course-data/getAll")
    public ResponseEntity<?> getAllCourse(@RequestParam(required = false) String search){
        return new ResponseEntity<>(this.courseDatumService.getAll(search), HttpStatus.OK);
    }

    @PostMapping("/course-data/add")
    public ResponseEntity<?> addCourseData(@Valid @RequestBody CourseDatumRequest rq){
        return new ResponseEntity<>(this.courseDatumService.addNewCourseData(rq), HttpStatus.OK);
    }

    @PutMapping ("/course-data/update/{id}")
    public ResponseEntity<?> updateCourseData(@Valid @RequestBody CourseDatumRequest rq,@PathVariable int id){
        return new ResponseEntity<>(this.courseDatumService.updateCourseData(rq, id), HttpStatus.OK);
    }

    @GetMapping("/course-date/remove-schedule/{id}")
    public ResponseEntity<?> removeSchedule(@PathVariable int id){
        return new ResponseEntity<>(this.courseDatumService.removeScheduleInfoByCourseDataId(id), HttpStatus.OK);
    }

    @DeleteMapping("/course-date/delete/{id}")
    public ResponseEntity<?> deleteCourseData(@PathVariable int id){
        return new ResponseEntity<>(this.courseDatumService.deleteCourseData(id), HttpStatus.OK);
    }
}