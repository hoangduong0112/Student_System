package com.hd.student.service;

import com.hd.student.payload.request.SemesterDetailRequest;
import com.hd.student.payload.response.ApiResponse;
import com.hd.student.payload.response.SemesterDetailsResponse;

import java.util.List;

public interface SemesterDetailService {
    List<SemesterDetailsResponse> getDetails(int id);

    ApiResponse addNewCourseInSemesterDetails(SemesterDetailRequest rq);

    ApiResponse addCourse(int id, int CourseId);
}
