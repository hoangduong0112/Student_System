package com.hd.student.service;

import com.hd.student.payload.response.SemesterDetailsResponse;

import java.util.List;

public interface SemesterDetailService {
    List<SemesterDetailsResponse> getDetails(int id);
}
