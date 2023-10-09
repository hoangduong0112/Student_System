package com.hd.student.controller.admin;

import com.hd.student.payload.request.ScheduleInfoRequest;
import com.hd.student.payload.request.StudyRoomRequest;
import com.hd.student.payload.response.ApiResponse;
import com.hd.student.service.ScheduleInfoService;
import com.hd.student.service.StudyRoomService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
@CrossOrigin
@RestController
@Secured("ADMIN")
@RequestMapping("/api/admin/")
public class AdminScheduleController {
    @Autowired
    private StudyRoomService studyRoomService;
    @Autowired
    private ScheduleInfoService scheduleInfoService;
    @PostMapping("/room/add")
    public ResponseEntity<?> addNewStudyRoom(@Valid @RequestBody StudyRoomRequest rq){
        return new ResponseEntity<>(this.studyRoomService.addStudyRoom(rq), HttpStatus.OK);
    }

    @PutMapping("/room/update/{id}")
    public ResponseEntity<?> updateStudyRoom(@Valid @RequestBody StudyRoomRequest rq, @PathVariable int id){
        return new ResponseEntity<>(this.studyRoomService.updateStudyRoom(rq, id), HttpStatus.OK);
    }

    @DeleteMapping("/room/delete/{id}")
    public ResponseEntity<?> deleteStudyRoom(@PathVariable Integer id) {
        ApiResponse rp = studyRoomService.deleteStudyRoom(id);
        return new ResponseEntity<>(rp, HttpStatus.OK);
    }

    @GetMapping("/room/get")
    public ResponseEntity<?> getAllRoom(@RequestParam(name = "isAvailable", required = false) Boolean isAvailable){
        return new ResponseEntity<>(this.studyRoomService.getAllRoom(isAvailable), HttpStatus.OK);
    }

    @GetMapping("/schedule-info/getall")
    public ResponseEntity<?> getAllScheduleInfo(){
        return ResponseEntity.ok(this.scheduleInfoService.getAll());
    }

    @PostMapping("/schedule-info/add")
    public ResponseEntity<?> addNewScheduleInfo(@Valid @RequestBody ScheduleInfoRequest rq){
        return ResponseEntity.ok(this.scheduleInfoService.addScheduleInfo(rq));
    }

    @PutMapping("/schedule-info/update/{id}")
    public ResponseEntity<?> updateScheduleInfo(@Valid @RequestBody ScheduleInfoRequest rq, @PathVariable int id){
        return ResponseEntity.ok(this.scheduleInfoService.updateScheduleInfo(rq, id));
    }

    @DeleteMapping("/schedule-info/delete/{id}")
    public ResponseEntity<?> deleteScheduleInfo(@PathVariable int id){
        return ResponseEntity.ok(this.scheduleInfoService.deleteSchedule(id));
    }

}
