package com.hd.student.service;

import com.hd.student.payload.request.CourseDataRequest;
import com.hd.student.payload.response.ApiResponse;
import com.hd.student.payload.response.CourseDataResponse;

import java.util.List;

public interface CourseDataService {
    List<CourseDataResponse> getAll(String name);
    ApiResponse addNewCourseData(CourseDataRequest rq);

    ApiResponse updateCourseData(CourseDataRequest rq, int id);
}
