package com.hd.student.service;

import com.hd.student.entity.OnlineService;
import com.hd.student.payload.ApiResponse;
import com.hd.student.payload.DiplomaCopyRequest;

public interface IOnlineService {
    public OnlineService addOnlineService(int userId, int serviceCate);

}
