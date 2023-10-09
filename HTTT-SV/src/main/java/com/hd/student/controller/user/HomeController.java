package com.hd.student.controller.user;

import com.hd.student.service.ServiceCateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/user")
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
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR')")
    @GetMapping("/service-cate/{id}")
    public ResponseEntity<?> getServiceCateById(@PathVariable int id) {
        return new ResponseEntity<>(this.serviceCateService.getServiceById(id), HttpStatus.OK);
    }

}
