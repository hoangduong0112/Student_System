package com.hd.student.controller.user;

import com.hd.student.entity.OnlineService;
import com.hd.student.entity.ServiceStatus;
import com.hd.student.exception.ResourceNotFoundException;
import com.hd.student.payload.request.DiplomaCopyRequest;
import com.hd.student.payload.request.StudCertificationRequest;
import com.hd.student.payload.request.TranscriptRequest;
import com.hd.student.payload.request.UnlockStudentRequest;
import com.hd.student.payload.response.*;
import com.hd.student.repository.OnlineServiceRepository;
import com.hd.student.security.UserPrincipal;
import com.hd.student.service.*;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/user/service")
public class OnlineServiceController {


    @Autowired
    private DiplomaCopyService diplomaCopyService;
    @Autowired
    private TranscriptService transcriptService;
    @Autowired
    private IOnlineService onlineService;

    @Autowired
    private StudCertificationService studCertificationService;
    @Autowired
    private UnlockStudentService unlockStudentService;

    @Autowired
    private ModelMapper modelMapper;


    @GetMapping("/my-request")
    public ResponseEntity<?> getMyOnlineService(Authentication auth){
        UserPrincipal u = (UserPrincipal) auth.getPrincipal();
        List<OnlineServiceResponse> onlineServiceResponses = onlineService.findAllByUserId(u.getId());
        return new ResponseEntity<>(onlineServiceResponses,HttpStatus.OK);
    }

    @PostMapping("/diploma/add")
    public ResponseEntity<ApiResponse> saveDiplomaCopy(Authentication auth, @RequestBody DiplomaCopyRequest rq){
        UserPrincipal u = (UserPrincipal) auth.getPrincipal();
        ApiResponse rs = this.diplomaCopyService.addNewDiplomaCopy(rq, u.getId());
        return new ResponseEntity<>(rs,HttpStatus.OK);
    }
    @PutMapping("diploma/update/{id}")
    public ResponseEntity<?> updateDiplomaCopy(Authentication auth, @RequestBody DiplomaCopyRequest rq, @PathVariable int id){
        UserPrincipal u = (UserPrincipal) auth.getPrincipal();
        ApiResponse rs = this.diplomaCopyService.updateMyDiplomaCopy(rq, id, u.getId());
        return new ResponseEntity<>(rs,HttpStatus.OK);
    }
    @GetMapping("/diploma/{serviceId}")
    public ResponseEntity<?> getDiplomaByServiceId(Authentication auth, @PathVariable int serviceId){
        UserPrincipal u = (UserPrincipal) auth.getPrincipal();
        DiplomaCopyResponse rp = this.diplomaCopyService.findByOnlineServiceId(serviceId, u.getId());

        return new ResponseEntity<>(rp, HttpStatus.OK);
    }


    @PostMapping("/transcript/add")
    public ResponseEntity<ApiResponse> saveTranscript(Authentication auth, @RequestBody TranscriptRequest rq){
        UserPrincipal u = (UserPrincipal) auth.getPrincipal();
        ApiResponse rs = this.transcriptService.addNewTranscript(rq, u.getId());
        return new ResponseEntity<>(rs,HttpStatus.OK);
    }
    @PutMapping("/transcript/update/{id}")
    public ResponseEntity<?> updateTranscript(Authentication auth, @RequestBody TranscriptRequest rq, @PathVariable int id) {
        UserPrincipal u = (UserPrincipal) auth.getPrincipal();
        ApiResponse rs = this.transcriptService.updateMyTranscript(rq, id, u.getId());
        return new ResponseEntity<>(rs, HttpStatus.OK);
    }
    @GetMapping("/transcript/{serviceId}")
    public ResponseEntity<?> getTranscriptByServiceId(Authentication auth, @PathVariable int serviceId){
        UserPrincipal u = (UserPrincipal) auth.getPrincipal();

        TranscriptResponse rp = this.transcriptService.findByOnlineServiceId(serviceId, u.getId());
        return new ResponseEntity<>(rp, HttpStatus.OK);
    }



    @PostMapping("/stud-certification/add")
    public ResponseEntity<ApiResponse> saveCertification(Authentication auth, @RequestBody StudCertificationRequest rq){
        UserPrincipal u = (UserPrincipal) auth.getPrincipal();
        ApiResponse rs = this.studCertificationService.addNewStudCertification(rq, u.getId());
        return new ResponseEntity<>(rs,HttpStatus.OK);
    }
    @PutMapping("/stud-certification/update/{id}")
    public ResponseEntity<?> updateStudCertification(Authentication auth, @RequestBody StudCertificationRequest rq, @PathVariable int id) {
        UserPrincipal u = (UserPrincipal) auth.getPrincipal();
        ApiResponse rs = this.studCertificationService.updateMyCertification(rq, id, u.getId());
        return new ResponseEntity<>(rs, HttpStatus.OK);
    }
    @GetMapping("/stud-certification/{serviceId}")
    public ResponseEntity<?> getStudCertificationByServiceId(Authentication auth, @PathVariable int serviceId){
        UserPrincipal u = (UserPrincipal) auth.getPrincipal();
        StudCertificationResponse rp = this.studCertificationService.findByOnlineServiceId(serviceId, u.getId());

        return new ResponseEntity<>(rp, HttpStatus.OK);
    }

    @PostMapping("/unlock-student/add")
    public ResponseEntity<ApiResponse> saveUnlockStudent(Authentication auth, @RequestBody UnlockStudentRequest rq){
        UserPrincipal u = (UserPrincipal) auth.getPrincipal();
        ApiResponse rs = this.unlockStudentService.addNewUnlockStudent(rq, u.getId());
        return new ResponseEntity<>(rs, HttpStatus.OK);
    }

    @PutMapping("/unlock/update/{id}")
    public ResponseEntity<?> updateUnlockStudent(Authentication auth, @RequestBody UnlockStudentRequest rq, @PathVariable int id) {
        UserPrincipal u = (UserPrincipal) auth.getPrincipal();
        ApiResponse rs = this.unlockStudentService.updateUnlockStudent(rq, id, u.getId());
        return new ResponseEntity<>(rs, HttpStatus.OK);
    }
    @GetMapping("/unlock-student/{serviceId}")
    public ResponseEntity<?> getUnlockStudentByServiceId(Authentication auth, @PathVariable int serviceId){
        UserPrincipal u = (UserPrincipal) auth.getPrincipal();

        UnlockStudentResponse rp = this.unlockStudentService.findByOnlineServiceId(serviceId, u.getId());
        return new ResponseEntity<>(rp, HttpStatus.OK);
    }

}
