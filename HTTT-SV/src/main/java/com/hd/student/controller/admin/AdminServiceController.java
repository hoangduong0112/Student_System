package com.hd.student.controller.admin;


import com.hd.student.entity.OnlineService;
import com.hd.student.payload.request.ServiceCateRequest;
import com.hd.student.payload.response.OnlineServiceResponse;
import com.hd.student.service.IOnlineService;
import com.hd.student.service.ScheduleInfoService;
import com.hd.student.service.ServiceCateService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/admin")
public class AdminServiceController {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private IOnlineService onlineService;
    @Autowired
    private ServiceCateService serviceCateService;


    @GetMapping("/get-request")
    public ResponseEntity<?> getRequest(){
        List<OnlineServiceResponse> rp = this.onlineService.findAll();
        return new ResponseEntity<>(rp, HttpStatus.OK);
    }

    @PutMapping("/service/{id}/accepted")
    public ResponseEntity<?> acceptTheRequest(@PathVariable int id){
        return new ResponseEntity<>(this.onlineService.acceptRequest(id), HttpStatus.OK);
    }

    @PutMapping("/service/{id}/deny")
    public ResponseEntity<?> denyTheRequest(@PathVariable int id){
        return new ResponseEntity<>(this.onlineService.denyRequest(id), HttpStatus.OK);
    }

    @DeleteMapping("/service/delete/{id}")
    public ResponseEntity<?> deleteRequest(@PathVariable int id){
        return new ResponseEntity<>(this.onlineService.deleteRequest(id), HttpStatus.OK);
    }

    @GetMapping("/service/search")
    public ResponseEntity<?> searchRequest(@DateTimeFormat(pattern = "dd/MM/yyyy") @RequestParam(value = "fromDate", required = false) LocalDate fromDate,
                                           @DateTimeFormat(pattern = "dd/MM/yyyy") @RequestParam(value = "toDate", required = false) LocalDate toDate){
        return new ResponseEntity<>(this.onlineService.searchRequest(fromDate, toDate), HttpStatus.OK);
    }

    @PostMapping("/service-cate/add")
    public ResponseEntity<?> addService(@RequestBody ServiceCateRequest rq) {
        return new ResponseEntity<>(this.serviceCateService.addServiceCate(rq), HttpStatus.OK);
    }

    @PutMapping("/service-cate/update/{id}")
    public ResponseEntity<?> updateService(@PathVariable int id,@RequestBody ServiceCateRequest rq) {
        return new ResponseEntity<>(this.serviceCateService.updateService(rq, id), HttpStatus.OK);
    }

    @PutMapping("/service-cate/change/{id}")
    public ResponseEntity<?> setAvailable(@PathVariable int id){
        return new ResponseEntity<>(this.serviceCateService.changeAvailableService(id), HttpStatus.OK);
    }

}
