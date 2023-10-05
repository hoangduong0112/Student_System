package com.hd.student.service;

import com.hd.student.payload.request.LectureRequest;
import com.hd.student.payload.response.ApiResponse;
import com.hd.student.payload.response.LectureResponse;

import java.util.List;

public interface LectureService {
    List<LectureResponse> getAll();
    LectureResponse getById(int id);
    LectureResponse addNewLecture(LectureRequest rq);
    LectureResponse updateLecture(LectureRequest rq, int id);

    ApiResponse deleteLecture(int id);
}
