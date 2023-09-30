package com.hd.student.serviceImpl;

import com.hd.student.entity.OnlineService;
import com.hd.student.entity.UnlockStudent;
import com.hd.student.exception.ResourceNotFoundException;
import com.hd.student.payload.request.UnlockStudentRequest;
import com.hd.student.payload.response.ApiResponse;
import com.hd.student.payload.response.UnlockStudentResponse;
import com.hd.student.repository.UnlockStudentRepository;
import com.hd.student.service.IOnlineService;
import com.hd.student.service.UnlockStudentService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UnlockStudentServiceImpl implements UnlockStudentService {

    @Autowired
    private ModelMapper modelMapper;


    @Autowired
    private UnlockStudentRepository unlockStudentRepository;
    @Autowired
    private IOnlineService onlineService;

    @Override
    @Transactional
    public ApiResponse addNewUnlockStudent(UnlockStudentRequest rq, int userId) {
        UnlockStudent us = new UnlockStudent();

        us = modelMapper.map(rq, UnlockStudent.class);

        OnlineService onlineService = this.onlineService.addOnlineService(userId,5);
        us.setOnlineService(onlineService);

        this.unlockStudentRepository.save(us);

        return new ApiResponse("Success", true);
    }

    public UnlockStudentResponse findByOnlineServiceId(int id, int userId) {
        OnlineService on = this.onlineService.findByIdWithAccess(id, userId);

        UnlockStudent tr = on.getUnlockStudent();
        if(tr == null)
            throw new ResourceNotFoundException("Không tìm thấy yêu cầu của bạn", "id", on.getId());
        return modelMapper.map(tr, UnlockStudentResponse.class);
    }

    @Override
    public ApiResponse updateUnlockStudent(UnlockStudentRequest rq, int id, int userId) {
        UnlockStudent us = this.unlockStudentRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Không tìm thấy yêu cầu cấp", "id", id));

        OnlineService on = us.getOnlineService();

        if (this.onlineService.checkAccess(on.getId(), userId)) {
            us = modelMapper.map(rq, UnlockStudent.class);
            us.setId(id);
            us.setOnlineService(on);
            this.unlockStudentRepository.save(us);
            return new ApiResponse("Success", true);
        }
        return new ApiResponse("Fail", false);
    }
}
