package com.hd.student.controller;

import com.hd.student.payload.ApiResponse;
import com.hd.student.payload.DiplomaCopyRequest;
import com.hd.student.payload.TranscriptRequest;
import com.hd.student.security.UserPrincipal;
import com.hd.student.service.DiplomaCopyService;
import com.hd.student.service.TranscriptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class OnlineServiceController {


    @Autowired
    private DiplomaCopyService diplomaCopyService;
    @Autowired
    private TranscriptService transcriptService;


    @PostMapping("/diploma/add")
    public ResponseEntity<ApiResponse> saveDiplomaCopy(Authentication auth, @RequestBody DiplomaCopyRequest rq){
        UserPrincipal u = (UserPrincipal) auth.getPrincipal();
        ApiResponse rs = this.diplomaCopyService.addNewDiplomaCopy(rq, u.getId());
        return new ResponseEntity<ApiResponse>(rs,HttpStatus.OK);
    }

    @PostMapping("/transcript/add")
    public ResponseEntity<ApiResponse> saveTranscript(Authentication auth, @RequestBody TranscriptRequest rq){
        UserPrincipal u = (UserPrincipal) auth.getPrincipal();
        ApiResponse rs = this.transcriptService.addNewTranscript(rq, u.getId());
        return new ResponseEntity<ApiResponse>(rs,HttpStatus.OK);
    }
}
