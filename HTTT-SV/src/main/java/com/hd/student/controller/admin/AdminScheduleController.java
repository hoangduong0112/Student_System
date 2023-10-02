package com.hd.student.controller.admin;

import com.hd.student.payload.request.StudyRoomRequest;
import com.hd.student.payload.response.ApiResponse;
import com.hd.student.service.StudyRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin
@RestController
@RequestMapping("/api/admin/")
public class AdminScheduleController {
    @Autowired
    private StudyRoomService studyRoomService;
    @PostMapping("/room/add")
    public ResponseEntity<?> addNewStudyRoom(@RequestBody StudyRoomRequest rq){
        return new ResponseEntity<>(this.studyRoomService.addStudyRoom(rq), HttpStatus.OK);
    }

    @PutMapping("/room/update/{id}")
    public ResponseEntity<?> updateStudyRoom(@RequestBody StudyRoomRequest rq, @PathVariable int id){
        return new ResponseEntity<>(this.studyRoomService.updateStudyRoom(rq, id), HttpStatus.OK);
    }

    @DeleteMapping("/room/delete/{id}")
    public ResponseEntity<?> deleteStudyRoom(@PathVariable Integer id) {
        ApiResponse rp = studyRoomService.deleteStudyRoom(id);
        return new ResponseEntity<>(rp, HttpStatus.OK);
    }

    @GetMapping("/room/getAll")
    public ResponseEntity<?> getAllRoom(){
        return new ResponseEntity<>(this.studyRoomService.getAllRoom(), HttpStatus.OK);
    }

    @GetMapping("/room/getAvailableRoom")
    public ResponseEntity<?> getAvailableRoom(){
        return new ResponseEntity<>(this.studyRoomService.getAllRoomAvailable(), HttpStatus.OK);
    }
}
