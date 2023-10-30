package com.hd.student.service;

import com.hd.student.entity.OnlineService;
import com.hd.student.payload.response.ApiResponse;
import com.hd.student.payload.response.OnlineServiceResponse;

import java.time.LocalDate;
import java.util.List;

public interface IOnlineService {
    public OnlineService addOnlineService(int userId, int serviceCate,double price);


    public List<OnlineServiceResponse> findAllByUserId(Integer userId);

    public OnlineService findByIdWithAccess(int id, int userId);



    boolean checkAccess(int id, int userId);

    List<OnlineServiceResponse> findAll();

    List<OnlineServiceResponse> findByCateId(int cateId);

    OnlineServiceResponse acceptService(int id);
    OnlineServiceResponse shippingService(int id);
    OnlineServiceResponse cancelService(int serviceId, int userId);
    OnlineServiceResponse receivedService(int id);
    ApiResponse deleteRequest(int id);

    List<OnlineServiceResponse> searchRequest(LocalDate fromDate, LocalDate toDate);
}
