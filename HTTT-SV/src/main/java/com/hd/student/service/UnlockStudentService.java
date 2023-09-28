package com.hd.student.service;

import com.hd.student.payload.request.UnlockStudentRequest;
import com.hd.student.payload.response.ApiResponse;
import com.hd.student.payload.response.TranscriptResponse;
import com.hd.student.payload.response.UnlockStudentResponse;

public interface UnlockStudentService {
    ApiResponse addNewUnlockStudent(UnlockStudentRequest rq, int userId);
    UnlockStudentResponse findByOnlineServiceId(int id, int userId);
}
