package com.hd.student.service;


import com.hd.student.payload.request.SemesterRequest;
import com.hd.student.payload.response.ApiResponse;
import com.hd.student.payload.response.SemesterResponse;

import java.util.List;

public interface SemesterService {
    List<SemesterResponse> getAll();
    SemesterResponse getSemesterById(int id);
    SemesterResponse addSemester(SemesterRequest rq);
    SemesterResponse updateSemester(SemesterRequest rq, int id);

    List<SemesterResponse> getAvailable();
    public ApiResponse deleteSemesterById(int id);
    SemesterResponse setFinish(int id);
}
