package com.hd.student.controller;

import com.hd.student.payload.response.OnlineServiceResponse;
import com.hd.student.security.UserPrincipal;
import com.hd.student.service.IOnlineService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin
@RestController
@Tag(name = "07. OnlineService", description = "Quản lý các yêu cầu")
@RequestMapping("/api/online-service/")
public class OnlineServiceController {
    @Autowired
    private IOnlineService onlineService;

    @Operation(
            summary = "Get All Request - Role Moderator",
            description = "Lấy tất cả yêu cầu hiện có"
    )
    @PreAuthorize("hasAuthority('MODERATOR')")
    @GetMapping("")
    public ResponseEntity<?> getRequest() {
        List<OnlineServiceResponse> rp = this.onlineService.findAll();
        return new ResponseEntity<>(rp, HttpStatus.OK);
    }

//    @Operation(
//            summary = "Get All Request By Cate - Role Moderator",
//            description = "Lấy tất cả yêu cầu hiện có by ServiceCateId"
//    )
//    @PreAuthorize("hasAuthority('MODERATOR')")
//    @GetMapping("/get-request/{cateId}")
//    public ResponseEntity<?> getRequest(@PathVariable int cateId) {
//        List<OnlineServiceResponse> rp = this.onlineService.findByCateId(cateId);
//        return new ResponseEntity<>(rp, HttpStatus.OK);
//    }

    @Operation(
            summary = "Accept OnlineService - Role Moderator",
            description = "Xác nhận yêu cầu"
    )
    @PreAuthorize("hasAuthority('MODERATOR')")
    @GetMapping("/{id}/accept")
    public ResponseEntity<?> acceptTheRequest(@PathVariable int id) {
        return new ResponseEntity<>(this.onlineService.acceptService(id), HttpStatus.OK);
    }

    @Operation(
            summary = "Delete OnlineService - Role Moderator",
            description = "Xoá yêu cầu"
    )
    @PreAuthorize("hasAuthority('MODERATOR')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRequest(@PathVariable int id) {
        return new ResponseEntity<>(this.onlineService.deleteRequest(id), HttpStatus.OK);
    }

//    @Operation(
//            summary = "Get OnlineService By CreatedDate- Role Moderator",
//            description = "Lấy yêu cầu theo CreatedDate"
//    )
//    @PreAuthorize("hasAuthority('MODERATOR')")
//    @GetMapping("/get-request/search")
//    public ResponseEntity<?> searchRequest(@DateTimeFormat(pattern = "dd/MM/yyyy") @RequestParam(value = "fromDate", required = false) LocalDate fromDate,
//                                           @DateTimeFormat(pattern = "dd/MM/yyyy") @RequestParam(value = "toDate", required = false) LocalDate toDate) {
//        return new ResponseEntity<>(this.onlineService.searchRequest(fromDate, toDate), HttpStatus.OK);
//    }

    @Operation(
            summary = "Cancel OnlineService",
            description = "Hủy yêu cầu"
    )
    @PreAuthorize("hasAuthority('USER') || hasAuthority('MODERATOR')")
    @GetMapping("/{id}/cancel")
    public ResponseEntity<?> cancelMyRequest(Authentication auth, @PathVariable int id){
        UserPrincipal u = (UserPrincipal) auth.getPrincipal();
        return new ResponseEntity<>(this.onlineService.cancelService(id,u.getId()), HttpStatus.OK);
    }
}
