package com.hd.student.service;

import com.hd.student.payload.response.ApiResponse;
import com.hd.student.payload.request.StudyRoomRequest;
import com.hd.student.payload.response.StudyRoomResponse;

import java.util.List;

public interface StudyRoomService {
    public ApiResponse addStudyRoom(StudyRoomRequest rq);
    public StudyRoomResponse updateStudyRoom(StudyRoomRequest rq, int id);

    public ApiResponse deleteStudyRoom(int id);

    public List<StudyRoomResponse> getAllRoom();
}
