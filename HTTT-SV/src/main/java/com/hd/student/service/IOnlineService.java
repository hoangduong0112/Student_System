package com.hd.student.service;

import com.hd.student.entity.OnlineService;
import com.hd.student.payload.response.OnlineServiceResponse;

import java.util.List;

public interface IOnlineService {
    public OnlineService addOnlineService(int userId, int serviceCate);

    public List<OnlineServiceResponse> findAllByUserId(Integer userId);

    public OnlineService findById(int id, int userId);

    boolean checkAccess(int id, int userId);
}
