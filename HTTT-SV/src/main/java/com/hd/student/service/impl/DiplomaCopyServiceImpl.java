package com.hd.student.service.impl;

import com.hd.student.entity.DiplomaCopy;
import com.hd.student.entity.OnlineService;
import com.hd.student.entity.ServiceStatus;
import com.hd.student.exception.AccessDeniedException;
import com.hd.student.exception.ResourceNotFoundException;
import com.hd.student.payload.response.ApiResponse;
import com.hd.student.payload.request.DiplomaCopyRequest;
import com.hd.student.payload.response.DiplomaCopyResponse;
import com.hd.student.repository.DiplomaCopyRepository;
import com.hd.student.service.DiplomaCopyService;
import com.hd.student.service.IOnlineService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class DiplomaCopyServiceImpl implements DiplomaCopyService {

    @Autowired
    private DiplomaCopyRepository diplomaCopyRepository;
    @Autowired
    private IOnlineService onlineService;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public ApiResponse addNewDiplomaCopy(DiplomaCopyRequest rq, int userId) {

        try {
            OnlineService onlineService = this.onlineService.addOnlineService(userId, 3);

            DiplomaCopy copy = new DiplomaCopy();

            copy = modelMapper.map(rq, DiplomaCopy.class);
//        copy.setCopy(rq.getCopy());
//        copy.setDiplomaYear(rq.getDiplomaYear());
//        copy.setDiplomaCode(rq.getDiplomaCode());
//        copy.setEmail(rq.getEmail());
//        copy.setPhoneContact(rq.getPhoneContact());
            copy.setOnlineService(onlineService);
            this.diplomaCopyRepository.save(copy);

            return new ApiResponse("Success", true);
        }catch (Exception ex){
            return new ApiResponse("Fail", false);
        }
    }

    @Override
    public DiplomaCopyResponse findByOnlineServiceId(int id, int userId) {
        OnlineService on = this.onlineService.findByIdWithAccess(id, userId);

        DiplomaCopy dp = on.getDiplomaCopy();
        if(dp == null)
            throw new ResourceNotFoundException("Không tìm thấy yêu cầu của bạn", "id", on.getId());
        return modelMapper.map(dp, DiplomaCopyResponse.class);
    }

    @Override
    public ApiResponse updateMyDiplomaCopy(DiplomaCopyRequest rq,int id, int userId) {
        DiplomaCopy copy = this.diplomaCopyRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Không tìm thấy yêu cầu", "id", id));
        OnlineService on = copy.getOnlineService();
        if(on.getStatus() == ServiceStatus.ONPROGRESS) {
            if (this.onlineService.checkAccess(on.getId(), userId)) {
                copy = modelMapper.map(rq, DiplomaCopy.class);

                copy.setId(id);
                copy.setOnlineService(on);
                this.diplomaCopyRepository.save(copy);
                return new ApiResponse("Success", true);
            }
            else
                throw new AccessDeniedException("Bạn không có quyền truy cập tài nguyên này");
        }
        else
            return new ApiResponse("Không thể sửa yêu cầu", true);
    }
}
