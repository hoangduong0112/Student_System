package com.hd.student.service;

import com.hd.student.payload.request.CourseDatumRequest;
import com.hd.student.payload.response.ApiResponse;
import com.hd.student.payload.response.CourseDatumResponse;

import java.util.List;

public interface CourseDatumService {
    List<CourseDatumResponse> getAll(String name);
    CourseDatumResponse addNewCourseData(CourseDatumRequest rq);

    CourseDatumResponse updateCourseData(CourseDatumRequest rq, int id);
}
