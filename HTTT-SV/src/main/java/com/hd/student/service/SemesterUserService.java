package com.hd.student.service;

import com.hd.student.payload.response.SemesterResponse;

import java.util.List;

public interface SemesterUserService {
    public List<SemesterResponse> getSemestersByUserId(Integer userId);
}
