package com.hd.student.serviceImpl;


import com.hd.student.entity.*;
import com.hd.student.exception.AccessDeniedException;
import com.hd.student.exception.ResourceNotFoundException;
import com.hd.student.payload.response.ApiResponse;
import com.hd.student.payload.response.OnlineServiceResponse;
import com.hd.student.repository.*;
import com.hd.student.service.IOnlineService;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private TranscriptRepository transcriptRepository;
    @Autowired
    private StudCertificationRepository studCertificationRepository;
    @Autowired
    private UnlockStudentRepository unlockStudentRepository;
    @Autowired
    private DiplomaCopyRepository diplomaCopyRepository;


    @Override
    public OnlineService addOnlineService(int userId, int serviceCate){
        User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","Id",userId));
        ServiceCate sc = this.serviceCateRepository.findById(serviceCate).orElseThrow(() -> new ResourceNotFoundException("ServiceCate","Id",serviceCate));

        OnlineService onlineService = new OnlineService();

        onlineService.setUser(user);
        onlineService.setStatus(ServiceStatus.ONPROGRESS);
        onlineService.setCreatedDate(LocalDate.now());
        onlineService.setIsShipped(false);
        onlineService.setServiceCate(sc);
        this.onlineServiceRepository.save(onlineService);

        return onlineService;
    }

    public boolean checkAccess(int id, int userId){
        OnlineService on = this.onlineServiceRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Online Service", "id", id));
        if(on.getUser().getId() != userId)
            throw new AccessDeniedException("Bạn không có quyền làm điều này");

        return true;
    }
//    public OnlineServiceResponse mapToResponse(OnlineService onlineService) {
//        OnlineServiceResponse response = new OnlineServiceResponse();
//        response.setId(onlineService.getId());
//        response.setCreatedDate(onlineService.getCreatedDate());
//        response.setStatus(onlineService.getStatus());
//        response.setIsShipped(onlineService.getIsShipped());
//        response.setServiceCateId(onlineService.getServiceCate().getId());
//
//        return response;
//    }
    @Override
    public List<OnlineServiceResponse> findAllByUserId(Integer userId) {
        List<OnlineService> services = onlineServiceRepository.findAllByUserId(userId);
        modelMapper.typeMap(OnlineService.class, OnlineServiceResponse.class).addMapping(OnlineService -> OnlineService.getServiceCate().getId(), OnlineServiceResponse::setServiceCateId);
        Converter<ServiceStatus, String> enumConverter =
                ctx -> ctx.getSource() == null ? null : ctx.getSource().name();
        modelMapper.addConverter(enumConverter);
        List<OnlineServiceResponse> rp = new ArrayList<>();
        for (OnlineService service : services) {
            OnlineServiceResponse map = modelMapper.map(service, OnlineServiceResponse.class);
            rp.add(map);
        }
        return rp;
//        return onlineServices.stream()
//                .map(this::mapToResponse)
//                .collect(Collectors.toList());
    }

    @Override
    public OnlineService findByIdWithAccess(int id, int userId) {
        OnlineService on = this.onlineServiceRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Online Service", "id", id));
        if(on.getUser().getId() != userId)
            throw new AccessDeniedException("Bạn không có quyền làm điều này");

        return on;
    }

    @Override
    public List<OnlineServiceResponse> findAll(){
        List<OnlineService> services = this.onlineServiceRepository.findAll();
        modelMapper.typeMap(OnlineService.class, OnlineServiceResponse.class).addMapping(OnlineService -> OnlineService.getServiceCate().getId(), OnlineServiceResponse::setServiceCateId);
        Converter<ServiceStatus, String> enumConverter =
                ctx -> ctx.getSource() == null ? null : ctx.getSource().name();
        modelMapper.addConverter(enumConverter);

        List<OnlineServiceResponse> rp = new ArrayList<>();
        for (OnlineService service : services) {
            OnlineServiceResponse map = modelMapper.map(service, OnlineServiceResponse.class);
            rp.add(map);
        }
        return rp;
    }

    @Override
    public OnlineServiceResponse acceptRequest(int id){
        OnlineService on = this.onlineServiceRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Không tìm thấy yêu cầu", "id", id)
        );
        on.setStatus(ServiceStatus.ACCEPT);
        this.onlineServiceRepository.save(on);
        modelMapper.typeMap(OnlineService.class, OnlineServiceResponse.class).addMapping(OnlineService -> OnlineService.getServiceCate().getId(), OnlineServiceResponse::setServiceCateId);
        Converter<ServiceStatus, String> enumConverter =
                ctx -> ctx.getSource() == null ? null : ctx.getSource().name();
        modelMapper.addConverter(enumConverter);
        return modelMapper.map(on, OnlineServiceResponse.class);

    }

    @Override
    public OnlineServiceResponse denyRequest(int id){
        OnlineService on = this.onlineServiceRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Không tìm thấy yêu cầu", "id", id)
        );
        on.setStatus(ServiceStatus.DENY);
        this.onlineServiceRepository.save(on);
        modelMapper.typeMap(OnlineService.class, OnlineServiceResponse.class).addMapping(OnlineService -> OnlineService.getServiceCate().getId(), OnlineServiceResponse::setServiceCateId);
        Converter<ServiceStatus, String> enumConverter =
                ctx -> ctx.getSource() == null ? null : ctx.getSource().name();
        modelMapper.addConverter(enumConverter);
        return modelMapper.map(on, OnlineServiceResponse.class);

    }

    @Override
    public OnlineServiceResponse cancelRequest(int serviceId, int userId){
        OnlineService on = this.findByIdWithAccess(serviceId, userId);
        on.setStatus(ServiceStatus.CANCEL);
        this.onlineServiceRepository.save(on);
        modelMapper.typeMap(OnlineService.class, OnlineServiceResponse.class)
                .addMapping(OnlineService -> OnlineService.getServiceCate().getId(),
                        OnlineServiceResponse::setServiceCateId);
        Converter<ServiceStatus, String> enumConverter =
                ctx -> ctx.getSource() == null ? null : ctx.getSource().name();
        modelMapper.addConverter(enumConverter);
        return modelMapper.map(on, OnlineServiceResponse.class);

    }

    @Override
    public ApiResponse deleteRequest(int id) {
        OnlineService on = this.onlineServiceRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Không tìm thấy yêu cầu", "id", id)
        );
        try {
            switch (on.getServiceCate().getId()) {
                case 1:
                    Transcript tr = on.getTranscript();
                    this.transcriptRepository.delete(tr);
                    break;
                case 2:
                    StudCertification sc = on.getStudCertification();
                    this.studCertificationRepository.delete(sc);
                    break;
                case 3:
                    DiplomaCopy dc = on.getDiplomaCopy();
                    this.diplomaCopyRepository.delete(dc);
                    break;
                case 5:
                    UnlockStudent us = on.getUnlockStudent();
                    this.unlockStudentRepository.delete(us);
                default:
                    break;

            }

            this.onlineServiceRepository.delete(on);
            return new ApiResponse("Success", true);
        }
        catch (Exception ex) {
            return new ApiResponse(ex.getMessage(), false);
        }
    }
}
