package com.hd.student.controller;

import com.hd.student.payload.request.TranscriptRequest;
import com.hd.student.payload.response.TranscriptResponse;
import com.hd.student.security.UserPrincipal;
import com.hd.student.service.TranscriptService;
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
@Tag(name = "04. Transcript", description = "Yêu cầu bảng điểm")
@RequestMapping("/api/transcript")
public class TranscriptController {
    @Autowired
    private TranscriptService transcriptService;

    @Operation(
            summary = "Add Transcript",
            description = "Thêm 1 yêu cầu về bảng điểm - Role User"
    )
    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("/add")
    public ResponseEntity<?> saveTranscript(Authentication auth, @Valid @RequestBody TranscriptRequest rq){
        UserPrincipal u = (UserPrincipal) auth.getPrincipal();
        return new ResponseEntity<>(this.transcriptService.addNewTranscript(rq, u.getId()), HttpStatus.OK);
    }

    @Operation(
            summary = "Update Transcript",
            description = "Chỉnh sửa 1 yêu cầu về bảng điểm - Role User"
    )
    @PreAuthorize("hasAuthority('USER')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateTranscript(Authentication auth, @Valid @RequestBody TranscriptRequest rq, @PathVariable int id) {
        UserPrincipal u = (UserPrincipal) auth.getPrincipal();
        return new ResponseEntity<>( this.transcriptService.updateMyTranscript(rq, id, u.getId()), HttpStatus.OK);
    }

    @Operation(
            summary = "Get Transcript By OnlineServiceId",
            description = "Lấy yêu cầu bảng điểm"
    )
    @GetMapping("/{serviceId}")
    public ResponseEntity<?> getTranscriptByServiceId(Authentication auth, @PathVariable int serviceId){
        UserPrincipal u = (UserPrincipal) auth.getPrincipal();

        TranscriptResponse rp = this.transcriptService.findByOnlineServiceId(serviceId, u.getId());
        return new ResponseEntity<>(rp, HttpStatus.OK);
    }
}
