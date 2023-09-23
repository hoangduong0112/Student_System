package com.hd.student.serviceImpl;

import com.hd.student.entity.DiplomaCopy;
import com.hd.student.entity.OnlineService;
import com.hd.student.payload.ApiResponse;
import com.hd.student.payload.DiplomaCopyRequest;
import com.hd.student.repository.DiplomaCopyRepository;
import com.hd.student.service.DiplomaCopyService;
import com.hd.student.service.IOnlineService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
@Transactional
public class DiplomaCopyServiceImpl implements DiplomaCopyService {

    @Autowired
    private DiplomaCopyRepository diplomaCopyRepository;
    @Autowired
    private IOnlineService onlineService;


    @Override
    public ApiResponse addNewDiplomaCopy(DiplomaCopyRequest rq, int userId) {
        OnlineService onlineService = this.onlineService.addOnlineService(userId,3);

        DiplomaCopy copy = new DiplomaCopy();

        copy.setCopy(rq.getCopy());
        copy.setDiplomaYear(rq.getDiplomaYear());
        copy.setDiplomaCode(rq.getDiplomaCode());
        copy.setEmail(rq.getEmail());
        copy.setPhoneContact(rq.getPhoneContact());
        copy.setOnlineService(onlineService);
        this.diplomaCopyRepository.save(copy);

        return new ApiResponse("Success", true);



    }
}
