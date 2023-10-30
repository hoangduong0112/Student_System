package com.hd.student.controller;

import com.hd.student.payload.request.ServiceCateRequest;
import com.hd.student.service.SemesterService;
import com.hd.student.service.ServiceCateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@Tag(name = "09. ServiceCate", description = "Quản lý loại dịch vụ")
@RequestMapping("/api/service-cate")
public class ServiceCateController {

    @Autowired
    private ServiceCateService serviceCateService;


    @Operation(
            summary = "Get All ServiceCate",
            description = "Lấy toàn bộ loại dịch vụ"
    )
    @GetMapping("")
    public ResponseEntity<?> getAllServiceCate() {
        return new ResponseEntity<>(this.serviceCateService.getAllDV(), HttpStatus.OK);
    }

    @Operation(
            summary = "Get ServiceCate By Id",
            description = "Lấy dịch vụ theo Id"
    )
    @GetMapping("/{id}")
    public ResponseEntity<?> getServiceCateById(@PathVariable int id) {
        return new ResponseEntity<>(this.serviceCateService.getServiceById(id), HttpStatus.OK);
    }

    @Operation(
            summary = "Update ServiceCate By Id",
            description = "Chỉnh sửa dịch vụ theo Id"
    )
    @PreAuthorize("hasAuthority('MODERATOR')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateService(@PathVariable int id, @Valid @RequestBody ServiceCateRequest rq) {
        return new ResponseEntity<>(this.serviceCateService.updateService(rq, id), HttpStatus.OK);
    }

    @Operation(
            summary = "Change Status ServiceCate By Id",
            description = "Thay đổi trạng thái đóng mở của Loại dịch vụ"
    )
    @PreAuthorize("hasAuthority('MODERATOR')")
    @GetMapping("/{id}/change")
    public ResponseEntity<?> setAvailable(@PathVariable int id) {
        return new ResponseEntity<>(this.serviceCateService.changeAvailableService(id), HttpStatus.OK);
    }
}
