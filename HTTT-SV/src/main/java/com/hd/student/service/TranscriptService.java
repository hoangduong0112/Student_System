package com.hd.student.service;


import com.hd.student.payload.ApiResponse;
import com.hd.student.payload.TranscriptRequest;

public interface TranscriptService {
    ApiResponse addNewTranscript(TranscriptRequest rq, int userId);
}
