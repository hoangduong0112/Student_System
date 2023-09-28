package com.hd.student.serviceImpl;


import com.hd.student.entity.*;
import com.hd.student.exception.AccessDeniedException;
import com.hd.student.exception.ResourceNotFoundException;
import com.hd.student.payload.response.OnlineServiceResponse;
import com.hd.student.repository.OnlineServiceRepository;
import com.hd.student.repository.ServiceCateRepository;
import com.hd.student.repository.UserRepository;
import com.hd.student.service.IOnlineService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OnlineServiceImpl implements IOnlineService {
    @Autowired
    private ServiceCateRepository serviceCateRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OnlineServiceRepository onlineServiceRepository;
    @Autowired
    private ModelMapper modelMapper;


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

    public OnlineServiceResponse mapToResponse(OnlineService onlineService) {
        OnlineServiceResponse response = new OnlineServiceResponse();
        response.setId(onlineService.getId());
        response.setCreatedDate(onlineService.getCreatedDate());
        response.setStatus(onlineService.getStatus());
        response.setIsShipped(onlineService.getIsShipped());
        response.setServiceCateId(onlineService.getServiceCate().getId());

        return response;
    }
    @Override
    public List<OnlineServiceResponse> findAllByUserId(Integer userId) {
        List<OnlineService> onlineServices = onlineServiceRepository.findAllByUserId(userId);
        return onlineServices.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public OnlineService findById(int id, int userId) {
        OnlineService on = this.onlineServiceRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Online Service", "id", id));
        if(on.getUser().getId() != userId)
            throw new AccessDeniedException("Bạn không có quyền làm điều này");

        return on;
    }
}
