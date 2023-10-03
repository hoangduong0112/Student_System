package com.hd.student.service;

import com.hd.student.payload.response.ApiResponse;
import com.hd.student.payload.response.SemesterUserResponse;

import java.util.List;

public interface SemesterUserService {
    public List<SemesterUserResponse> getSemestersByUserId(Integer userId);
    ApiResponse addSemesterForUser(int userId, int semesterId);
    ApiResponse deleteSemesterUser(int userId, int semesterId);
}
