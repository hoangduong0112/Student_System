package com.hd.student.controller.admin;


import com.hd.student.entity.OnlineService;
import com.hd.student.payload.response.OnlineServiceResponse;
import com.hd.student.service.IOnlineService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/admin/")
public class AdminServiceController {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private IOnlineService onlineService;


    @GetMapping("/get-request/")
    public ResponseEntity<?> getRequest(){
        List<OnlineServiceResponse> rp = this.onlineService.findAll();
        return new ResponseEntity<>(rp, HttpStatus.OK);
    }

    @PutMapping("/service/{id}/accepted")
    public ResponseEntity<?> acceptTheRequest(@PathVariable int id){
        return new ResponseEntity<>(this.onlineService.acceptRequest(id), HttpStatus.OK);
    }

    @PutMapping("/service/{id}/deny")
    public ResponseEntity<?> denyTheRequest(@PathVariable int id){
        return new ResponseEntity<>(this.onlineService.denyRequest(id), HttpStatus.OK);
    }

    @DeleteMapping("/service/delete/{id}")
    public ResponseEntity<?> deleteRequest(@PathVariable int id){
        return new ResponseEntity<>(this.onlineService.deleteRequest(id), HttpStatus.OK);
    }
}
