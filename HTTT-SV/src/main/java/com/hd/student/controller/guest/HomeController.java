package com.hd.student.controller.guest;

import com.hd.student.service.ServiceCateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/guest")
@RestController
@CrossOrigin
@RestControllerAdvice
public class HomeController {

    @Autowired
    private ServiceCateService serviceCateService;

    @GetMapping("/service-cate")
    public ResponseEntity<?> getAllServiceCate() {
        return new ResponseEntity<>(this.serviceCateService.getAllDV(), HttpStatus.OK);
    }

    @GetMapping("/service-cate/{id}")
    public ResponseEntity<?> getServiceCateById(@PathVariable int id) {
        return new ResponseEntity<>(this.serviceCateService.getServiceById(id), HttpStatus.OK);
    }

}
