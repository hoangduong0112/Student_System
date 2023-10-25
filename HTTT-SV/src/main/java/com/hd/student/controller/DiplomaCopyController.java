package com.hd.student.controller;

import com.hd.student.payload.request.DiplomaCopyRequest;
import com.hd.student.payload.response.DiplomaCopyResponse;
import com.hd.student.security.UserPrincipal;
import com.hd.student.service.DiplomaCopyService;
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
@Tag(name = "06. DiplomaCopy", description = "Yêu cầu cấp bảng sao bằng tốt nghiệp")
@RequestMapping("/api/diploma")
public class DiplomaCopyController {

    @Autowired
    private DiplomaCopyService diplomaCopyService;

    @Operation(
            summary = "Add DiplomaCopy",
            description = "Thêm 1 yêu cầu về cấp bảng sao bằng tốt nghiệp - Role User"
    )
    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("/add")
    public ResponseEntity<?> saveDiplomaCopy(Authentication auth, @Valid @RequestBody DiplomaCopyRequest rq){
        UserPrincipal u = (UserPrincipal) auth.getPrincipal();
        return new ResponseEntity<>(this.diplomaCopyService.addNewDiplomaCopy(rq, u.getId()), HttpStatus.OK);
    }


    @Operation(
            summary = "Update DiplomaCopy",
            description = "Chỉnh sửa 1 yêu cầu về cấp bảng sao bằng tốt nghiệp - Role User"
    )
    @PreAuthorize("hasAuthority('USER')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateDiplomaCopy(Authentication auth, @Valid @RequestBody DiplomaCopyRequest rq, @PathVariable int id){
        UserPrincipal u = (UserPrincipal) auth.getPrincipal();
        return new ResponseEntity<>(this.diplomaCopyService.updateMyDiplomaCopy(rq, id, u.getId())
                ,HttpStatus.OK);
    }

    @Operation(
            summary = "Get DiplomaCopy By OnlineServiceId",
            description = "Lấy yêu cầu về cấp bảng sao bằng tốt nghiệp"
    )
    @GetMapping("/{serviceId}")
    public ResponseEntity<?> getDiplomaByServiceId(Authentication auth, @PathVariable int serviceId){
        UserPrincipal u = (UserPrincipal) auth.getPrincipal();
        DiplomaCopyResponse rp = this.diplomaCopyService.findByOnlineServiceId(serviceId, u.getId());

        return new ResponseEntity<>(rp, HttpStatus.OK);
    }
}
