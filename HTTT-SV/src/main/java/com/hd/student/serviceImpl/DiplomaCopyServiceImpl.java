package com.hd.student.serviceImpl;

import com.hd.student.entity.DiplomaCopy;
import com.hd.student.entity.OnlineService;
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
        OnlineService onlineService = this.onlineService.addOnlineService(userId,3);

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
    }

    @Override
    public DiplomaCopyResponse findByOnlineServiceId(int id, int userId) {
        OnlineService on = this.onlineService.findById(id, userId);

        DiplomaCopy dp = on.getDiplomaCopy();
        if(dp == null)
            throw new ResourceNotFoundException("Không tìm thấy yêu cầu của bạn", "id", on.getId());
        return modelMapper.map(dp, DiplomaCopyResponse.class);
    }

    @Override
    public ApiResponse updateMyDiplomaCopy(DiplomaCopyRequest rq, int userId) {
        DiplomaCopy copy = modelMapper.map(rq, DiplomaCopy.class);

        this.diplomaCopyRepository.save(copy);
        return new ApiResponse("Success", true);
    }
}
