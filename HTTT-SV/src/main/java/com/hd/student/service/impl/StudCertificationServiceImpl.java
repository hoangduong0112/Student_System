package com.hd.student.service.impl;

import com.hd.student.entity.OnlineService;
import com.hd.student.entity.StudCertification;
import com.hd.student.exception.ResourceNotFoundException;
import com.hd.student.payload.response.ApiResponse;
import com.hd.student.payload.request.StudCertificationRequest;
import com.hd.student.payload.response.StudCertificationResponse;
import com.hd.student.repository.StudCertificationRepository;
import com.hd.student.service.IOnlineService;
import com.hd.student.service.StudCertificationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudCertificationServiceImpl implements StudCertificationService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IOnlineService onlineService;
    @Autowired
    private StudCertificationRepository studCertificationRepository;


    @Override
    public ApiResponse addNewStudCertification(StudCertificationRequest rq, int userId) {
        StudCertification tr = modelMapper.map(rq, StudCertification.class);

        OnlineService onlineService = this.onlineService.addOnlineService(userId,2);


        tr.setOnlineService(onlineService);
        this.studCertificationRepository.save(tr);
        return new ApiResponse("Success", true);
    }

    @Override
    public StudCertificationResponse findByOnlineServiceId(int id, int userId) {
        OnlineService on = this.onlineService.findByIdWithAccess(id, userId);
        StudCertification st = on.getStudCertification();
        if(st == null)
            throw new ResourceNotFoundException("Không tìm thấy yêu cầu của bạn", "id", on.getId());
        return modelMapper.map(st, StudCertificationResponse.class);
    }

    @Override
    public ApiResponse updateMyCertification(StudCertificationRequest rq, int id, int userId) {
        StudCertification sc = this.studCertificationRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Không tìm thấy yêu cầu cấp", "id", id));
        OnlineService on = sc.getOnlineService();

        if (this.onlineService.checkAccess(on.getId(), userId)) {
            sc = modelMapper.map(rq, StudCertification.class);
            sc.setId(id);
            sc.setOnlineService(on);
            this.studCertificationRepository.save(sc);
            return new ApiResponse("Success", true);
        }
        return new ApiResponse("Fails", false);
    }
}
