package com.hd.student.controller;

import com.hd.student.payload.response.ApiResponse;
import com.hd.student.payload.request.StudyRoomRequest;
import com.hd.student.payload.response.StudyRoomResponse;
import com.hd.student.service.StudyRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/admin/room")
@RestController
@CrossOrigin
public class StudyRoomController {

    @Autowired
    private StudyRoomService studyRoomService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addNewStudyRoom(@RequestBody StudyRoomRequest rq){
        ApiResponse rp = this.studyRoomService.addStudyRoom(rq);
        return new ResponseEntity<ApiResponse>(rp, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateStudyRoom(@RequestBody StudyRoomRequest rq, @PathVariable int id){
        return new ResponseEntity<>(this.studyRoomService.updateStudyRoom(rq, id), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteStudyRoom(@PathVariable Integer id) {
        ApiResponse rp = studyRoomService.deleteStudyRoom(id);
        return new ResponseEntity<ApiResponse>(rp, HttpStatus.OK);
    }

    @GetMapping("/getall")
    public ResponseEntity<List<StudyRoomResponse>> getAllRoom(){
        return new ResponseEntity<>(this.studyRoomService.getAllRoom(), HttpStatus.OK);
    }
}
