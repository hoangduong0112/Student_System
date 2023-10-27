package com.hd.student.controller;

import com.hd.student.payload.request.StudCertificationRequest;
import com.hd.student.payload.response.StudCertificationResponse;
import com.hd.student.security.UserPrincipal;
import com.hd.student.service.StudCertificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@Tag(name = "05. Stud-Certification", description = "Yêu cầu chứng nhận sinh viên")
@RequestMapping("/api/stud-certificate")
public class StudCertificationController {
    @Autowired
    private StudCertificationService studCertificationService;

    @Operation(
            summary = "Add Stud-Certification",
            description = "Thêm 1 yêu cầu về chứng nhận sinh viên - Role User"
    )
    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("")
    public ResponseEntity<?> saveCertification(Authentication auth, @Valid @RequestBody StudCertificationRequest rq){
        UserPrincipal u = (UserPrincipal) auth.getPrincipal();
        return new ResponseEntity<>(this.studCertificationService.addNewStudCertification(rq, u.getId())
                , HttpStatus.OK);
    }

    @Operation(
            summary = "Update Stud-Certification",
            description = "Thêm 1 yêu cầu về chứng nhận sinh viên - Role User"
    )
    @PreAuthorize("hasAuthority('USER')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateStudCertification(Authentication auth, @Valid @RequestBody StudCertificationRequest rq, @PathVariable int id) {
        UserPrincipal u = (UserPrincipal) auth.getPrincipal();
        return new ResponseEntity<>(this.studCertificationService.updateMyCertification(rq,id, u.getId()), HttpStatus.OK);
    }

    @Operation(
            summary = "Get Stud-Certification By OnlineServiceId",
            description = "Lấy yêu cầu chứng nhận sinh viên"
    )
    @GetMapping("/{serviceId}")
    public ResponseEntity<?> getStudCertificationByServiceId(Authentication auth, @PathVariable int serviceId){
        UserPrincipal u = (UserPrincipal) auth.getPrincipal();
        StudCertificationResponse rp = this.studCertificationService.findByOnlineServiceId(serviceId, u.getId());

        return new ResponseEntity<>(rp, HttpStatus.OK);
    }

}
