package com.hd.student.service.impl;

import com.hd.student.entity.OnlineService;
import com.hd.student.entity.ServiceCate;
import com.hd.student.entity.StudCertification;
import com.hd.student.entity.enums.ServiceStatus;
import com.hd.student.exception.ResourceNotFoundException;
import com.hd.student.payload.response.ApiResponse;
import com.hd.student.payload.request.StudCertificationRequest;
import com.hd.student.payload.response.StudCertificationResponse;
import com.hd.student.repository.ServiceCateRepository;
import com.hd.student.repository.StudCertificationRepository;
import com.hd.student.service.IOnlineService;
import com.hd.student.service.StudCertificationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudCertificationServiceImpl implements StudCertificationService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IOnlineService onlineService;
    @Autowired
    private StudCertificationRepository studCertificationRepository;
    @Autowired
    private ServiceCateRepository serviceCateRepository;

    @Override
    @Transactional
    public ApiResponse addNewStudCertification(StudCertificationRequest rq, int userId) {
        try {
            ServiceCate serviceCate = this.serviceCateRepository.findById(2).orElseThrow(
                    () -> new ResourceNotFoundException("Không tìm thấy loại dịch vụ")
            );
            StudCertification tr = modelMapper.map(rq, StudCertification.class);

            OnlineService onlineService = this.onlineService.addOnlineService(userId, 2
                    , serviceCate.getPrice() * tr.getEngCopy());
            //chi tinh tien ban english


            tr.setOnlineService(onlineService);
            this.studCertificationRepository.save(tr);
            return new ApiResponse("Success", true);
        } catch (RuntimeException ex) {
            throw new RuntimeException("Lỗi server");
        }
    }

    @Override
    public StudCertificationResponse findByOnlineServiceId(int id, int userId) {
        OnlineService on = this.onlineService.findByIdWithAccess(id, userId);
        StudCertification st = on.getStudCertification();
        if (st == null)
            throw new ResourceNotFoundException("Không tìm thấy yêu cầu của bạn");
        return modelMapper.map(st, StudCertificationResponse.class);
    }

    @Override
    public ApiResponse updateMyCertification(StudCertificationRequest rq, int id, int userId) {
        StudCertification sc = this.studCertificationRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Không tìm thấy yêu cầu cấp"));
        OnlineService on = sc.getOnlineService();

        if (on.getStatus() == ServiceStatus.PENDING) {
            if (this.onlineService.checkAccess(on.getId(), userId)) {
                sc = modelMapper.map(rq, StudCertification.class);
                sc.setId(id);
                sc.setOnlineService(on);
                this.studCertificationRepository.save(sc);
                return new ApiResponse("Success", true);
            } else throw new AccessDeniedException("Bạn không có quyền làm điều này");
        }
        return new ApiResponse("Yêu cầu đã xác nhận hoặc hủy bỏ", true);
    }
}
