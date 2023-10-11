package com.hd.student.controller.guest;

import com.hd.student.payload.request.ServiceCateRequest;
import com.hd.student.service.ServiceCateService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/guest/service-cate")
@RestController
@CrossOrigin
@Tag(name = "02. Home", description = "Home")
public class HomeController {


    @Autowired
    private ServiceCateService serviceCateService;

    @GetMapping("/get")
    public ResponseEntity<?> getAllServiceCate() {
        return new ResponseEntity<>(this.serviceCateService.getAllDV(), HttpStatus.OK);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getServiceCateById(@PathVariable int id) {
        return new ResponseEntity<>(this.serviceCateService.getServiceById(id), HttpStatus.OK);
    }

}
