package com.hd.student.controller;

import com.hd.student.payload.request.UnlockStudentRequest;
import com.hd.student.payload.response.UnlockStudentResponse;
import com.hd.student.security.UserPrincipal;
import com.hd.student.service.UnlockStudentService;
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
@Tag(name = "03. UnlockStudent", description = "Yêu cầu mở khóa sinh viên")
@RequestMapping("/api/unlock-student")
public class UnlockStudentController {
    @Autowired
    private UnlockStudentService unlockStudentService;

    @Operation(
            summary = "Add UnlockStudent",
            description = "Thêm 1 yêu cầu về mở khóa sinh viên - Role User"
    )
    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("/add")
    public ResponseEntity<?> saveUnlockStudent(Authentication auth, @Valid @RequestBody UnlockStudentRequest rq){
        UserPrincipal u = (UserPrincipal) auth.getPrincipal();
        return new ResponseEntity<>(this.unlockStudentService.addNewUnlockStudent(rq, u.getId()), HttpStatus.OK);
    }

    @Operation(
            summary = "Update UnlockStudent",
            description = "Sửa 1 yêu cầu mở khóa sinh viên - Role User"
    )
    @PreAuthorize("hasAuthority('USER')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUnlockStudent(Authentication auth,@Valid @RequestBody UnlockStudentRequest rq, @PathVariable int id) {
        UserPrincipal u = (UserPrincipal) auth.getPrincipal();
        return new ResponseEntity<>(this.unlockStudentService.updateUnlockStudent(rq, id, u.getId()), HttpStatus.OK);
    }

    @Operation(
            summary = "Get UnlockStudent By OnlineServiceId",
            description = "Lấy yêu cầu mở khóa sinh viên"
    )
    @GetMapping("/{serviceId}")
    public ResponseEntity<?> getUnlockStudentByServiceId(Authentication auth, @PathVariable int serviceId){
        UserPrincipal u = (UserPrincipal) auth.getPrincipal();

        UnlockStudentResponse rp = this.unlockStudentService.findByOnlineServiceId(serviceId, u.getId());
        return new ResponseEntity<>(rp, HttpStatus.OK);
    }

}
