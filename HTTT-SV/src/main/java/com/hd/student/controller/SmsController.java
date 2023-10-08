package com.hd.student.controller;

import com.hd.student.utils.TwillioUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/sms")
public class SmsController {

    @Autowired
    private TwillioUtils twillioUtils;

    @PostMapping("/send")
    public ResponseEntity<?> sendSMS(){
        return ResponseEntity.ok().body(this.twillioUtils.sendSMS());
    }
}
