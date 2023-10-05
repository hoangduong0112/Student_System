package com.hd.student.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payment")
@CrossOrigin
public class PaymentController {

    @GetMapping("/create-payment")
    public ResponseEntity<?> createPayment(){
        return null;
    }
}
