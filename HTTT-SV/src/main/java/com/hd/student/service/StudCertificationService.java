package com.hd.student.service;

import com.hd.student.payload.request.TranscriptRequest;
import com.hd.student.payload.response.ApiResponse;
import com.hd.student.payload.request.StudCertificationRequest;
import com.hd.student.payload.response.StudCertificationResponse;

public interface StudCertificationService {
    public ApiResponse addNewStudCertification(StudCertificationRequest rq, int userId);

    StudCertificationResponse findByOnlineServiceId(int id, int userId);

    ApiResponse updateMyCertification(StudCertificationRequest rq, int id, int userId);
}
