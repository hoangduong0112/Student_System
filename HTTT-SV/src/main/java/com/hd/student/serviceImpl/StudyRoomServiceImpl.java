package com.hd.student.serviceImpl;

import com.hd.student.entity.StudyRoom;
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
import java.util.stream.Collectors;

@Service
public class StudyRoomServiceImpl implements StudyRoomService {

    @Autowired
    private StudyRoomRepository studyRoomRepository;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public ApiResponse addStudyRoom(StudyRoomRequest rq) {
        StudyRoom studyRoom = modelMapper.map(rq, StudyRoom.class);
        if(studyRoomRepository.findByStudyRoomName(studyRoom.getStudyRoomName()).isPresent())
            throw new ResourceExistException("StudyRoomName", studyRoom.getStudyRoomName());
        studyRoomRepository.save(studyRoom);

        return new ApiResponse("Thêm 1 phòng học thành công", true);
    }

    @Override
    public StudyRoomResponse updateStudyRoom(StudyRoomRequest rq, int id) {
        StudyRoom studyRoom = modelMapper.map(rq, StudyRoom.class);
        if(studyRoomRepository.findByStudyRoomName(studyRoom.getStudyRoomName()).isPresent())
            throw new ResourceExistException("StudyRoomName", studyRoom.getStudyRoomName());
        else if(studyRoomRepository.findById(id).isEmpty())
            throw new ResourceNotFoundException("StudyRoom","id", id);
        studyRoom.setId(id);

        studyRoomRepository.save(studyRoom);

        return modelMapper.map(studyRoom, StudyRoomResponse.class);

    }

    @Override
    public ApiResponse deleteStudyRoom(int id) {
        StudyRoom st = this.studyRoomRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("StudyRoom", "id", id));

        studyRoomRepository.delete(st);
        return new ApiResponse("Xóa thành công", true);
    }

    @Override
    public List<StudyRoomResponse> getAllRoom() {
        List<StudyRoom> studyRooms = this.studyRoomRepository.findAll();
        return studyRooms.stream()
                .map(studyRoom -> modelMapper.map(studyRoom, StudyRoomResponse.class))
                .collect(Collectors.toList());

    }
}
