package com.hd.student.service;

import com.hd.student.entity.User;
import com.hd.student.payload.ApiResponse;
import com.hd.student.payload.DiplomaCopyRequest;

public interface DiplomaCopyService {
    ApiResponse addNewDiplomaCopy(DiplomaCopyRequest rq,int userId);
}
