package com.hd.student.controller;

import com.hd.student.entity.ServiceCate;
import com.hd.student.serviceImpl.ServiceCateServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/v1")
@RestController
@CrossOrigin
public class HomeController {


    @Autowired
    private ServiceCateServiceImpl serviceCateServiceImpl;

    @GetMapping("/dichvu")
    public List<ServiceCate> getAllMovies() {
        return this.serviceCateServiceImpl.getAllDV();
    }
}
