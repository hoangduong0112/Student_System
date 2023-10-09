package com.hd.student.service.impl;

import com.hd.student.entity.StudyRoom;
import com.hd.student.exception.BadRequestException;
import com.hd.student.exception.ResourceExistException;
import com.hd.student.exception.ResourceNotFoundException;
import com.hd.student.payload.response.ApiResponse;
import com.hd.student.payload.request.StudyRoomRequest;
import com.hd.student.payload.response.StudyRoomResponse;
import com.hd.student.repository.StudyRoomRepository;
import com.hd.student.service.StudyRoomService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudyRoomServiceImpl implements StudyRoomService {

    @Autowired
    private StudyRoomRepository studyRoomRepository;
    @Autowired
    private ModelMapper modelMapper;

    public boolean isNameUnique(String name, Integer id) {
        Optional<StudyRoom> existStudyRoom = studyRoomRepository.findByStudyRoomNameIgnoreCase(name);

        return existStudyRoom.map(studyRoom -> studyRoom.getId().equals(id)).orElse(true);

    }
    @Override
    public StudyRoomResponse addStudyRoom(StudyRoomRequest rq) {
        StudyRoom studyRoom = modelMapper.map(rq, StudyRoom.class);
        if(isNameUnique(studyRoom.getStudyRoomName(), null))
            throw new ResourceExistException("Phòng học đã tồn tại");
        studyRoomRepository.save(studyRoom);

        return modelMapper.map(studyRoom, StudyRoomResponse.class);
    }

    @Override
    public StudyRoomResponse updateStudyRoom(StudyRoomRequest rq, int id) {
        StudyRoom studyRoom = modelMapper.map(rq, StudyRoom.class);
        if(isNameUnique(studyRoom.getStudyRoomName(), id))
            throw new ResourceExistException("Phòng học đã trùng tên");
        else if(studyRoomRepository.findById(id).isEmpty())
            throw new ResourceNotFoundException("Phòng học không tồn tại");
        studyRoom.setId(id);

        studyRoomRepository.save(studyRoom);

        return modelMapper.map(studyRoom, StudyRoomResponse.class);

    }

    @Override
    public ApiResponse deleteStudyRoom(int id) {
        StudyRoom st = this.studyRoomRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Không tìm thấy phòng học"));
        if(st.getScheduleInfos().isEmpty())
            studyRoomRepository.delete(st);
        return new ApiResponse("Xóa thành công", true);
    }

    @Override
    public List<StudyRoomResponse> getAllRoom(Boolean isAvailable) {
        List<StudyRoom> studyRooms;
        if(isAvailable!=null)
            studyRooms = studyRoomRepository.findByIsAvailable(isAvailable);
        else
            studyRooms = studyRoomRepository.findAll();
        return studyRooms.stream()
                .map(studyRoom -> modelMapper.map(studyRoom, StudyRoomResponse.class))
                .collect(Collectors.toList());

    }
}
