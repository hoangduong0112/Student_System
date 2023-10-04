package com.hd.student.service;

import com.hd.student.payload.request.ScheduleInfoRequest;
import com.hd.student.payload.response.ApiResponse;
import com.hd.student.payload.response.ScheduleInfoResponse;

import java.util.List;

public interface ScheduleInfoService {
    List<ScheduleInfoResponse> getAll();

    ScheduleInfoResponse getById(int id);

    ScheduleInfoResponse addScheduleInfo(ScheduleInfoRequest rq);
    ScheduleInfoResponse updateScheduleInfo(ScheduleInfoRequest rq, int id);

    ApiResponse deleteSchedule(int id);
}
