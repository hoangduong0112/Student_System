package com.hd.student.serviceImpl;


import com.hd.student.entity.OnlineService;
import com.hd.student.entity.ServiceCate;
import com.hd.student.entity.User;
import com.hd.student.exception.ResourceNotFoundException;
import com.hd.student.repository.OnlineServiceRepository;
import com.hd.student.repository.ServiceCateRepository;
import com.hd.student.repository.UserRepository;
import com.hd.student.service.IOnlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class OnlineServiceImpl implements IOnlineService {
    @Autowired
    private ServiceCateRepository serviceCateRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OnlineServiceRepository onlineServiceRepository;

    @Override
    public OnlineService addOnlineService(int userId, int serviceCate){
        User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","Id",userId));
        ServiceCate sc = this.serviceCateRepository.findById(serviceCate).orElseThrow(() -> new ResourceNotFoundException("ServiceCate","Id",serviceCate));

        OnlineService onlineService = new OnlineService();

        onlineService.setUser(user);
        onlineService.setStatus("None");
        onlineService.setCreatedDate(LocalDate.now());
        onlineService.setIsShipped(false);
        onlineService.setServiceCate(sc);
        this.onlineServiceRepository.save(onlineService);

        return onlineService;
    }

}
