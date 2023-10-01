package com.hd.student.service;

import com.hd.student.entity.Course;
import com.hd.student.payload.request.CourseRequest;
import com.hd.student.payload.response.ApiResponse;
import com.hd.student.payload.response.CourseResponse;

import java.util.List;
import java.util.Map;

public interface CourseService {
    List<CourseResponse> getAllCourse(String search);

    CourseResponse addNewCourse(CourseRequest rq);

    CourseResponse updateCourse(CourseRequest rq, int id);

    ApiResponse deleteCourse(int id);
}
