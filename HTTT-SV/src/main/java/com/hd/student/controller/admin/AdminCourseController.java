package com.hd.student.controller.admin;

import com.hd.student.payload.request.CourseRequest;
import com.hd.student.payload.request.LectureRequest;
import com.hd.student.service.CourseService;
import com.hd.student.service.LectureService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@Tag(name = "07. Course/Lecture", description = "QL mon hoc va giang vien")
@RequestMapping("/api/admin/")
public class AdminCourseController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private LectureService lectureService;

    //Quan ly môn học
    @GetMapping("/course/getall")
    public ResponseEntity<?> getAllCourse(@RequestParam(required = false) String search){
        return new ResponseEntity<>(this.courseService.getAllCourse(search), HttpStatus.OK);
    }

    @GetMapping("/course/{id}")
    public ResponseEntity<?> getCourseById(@PathVariable int id){
        return new ResponseEntity<>(this.courseService.getCourseById(id), HttpStatus.OK);
    }

    @PostMapping("/course/add")
    public ResponseEntity<?> addNewCourse(@Valid @RequestBody CourseRequest rq){
        return new ResponseEntity<>(this.courseService.addNewCourse(rq), HttpStatus.OK);
    }
    @PutMapping("/course/update/{id}")
    public ResponseEntity<?>  updateCourse(@Valid @RequestBody CourseRequest rq, @PathVariable int id){
        return new ResponseEntity<>(this.courseService.updateCourse(rq, id), HttpStatus.OK);
    }

    @DeleteMapping ("/course/delete/{id}")
    public ResponseEntity<?>  deleteCourse(@PathVariable int id){
        return new ResponseEntity<>(this.courseService.deleteCourse(id), HttpStatus.OK);
    }

    //Quản lý thông tin giảng viên
    @GetMapping("/lecture/getAll")
    public ResponseEntity<?> getAll(){
        return new ResponseEntity<>(this.lectureService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/lecture/add")
    public ResponseEntity<?> addNewLecture(@Valid @RequestBody LectureRequest rq){
        return new ResponseEntity<>(this.lectureService.addNewLecture(rq), HttpStatus.OK);
    }
    @PutMapping("/lecture/update/{id}")
    public ResponseEntity<?>  updateLecture(@Valid @RequestBody LectureRequest rq, @PathVariable int id){
        return new ResponseEntity<>(this.lectureService.updateLecture(rq, id), HttpStatus.OK);
    }

    @DeleteMapping ("/lecture/delete/{id}")
    public ResponseEntity<?>  deleteLecture(@PathVariable int id){
        return new ResponseEntity<>(this.lectureService.deleteLecture(id), HttpStatus.OK);
    }
}
