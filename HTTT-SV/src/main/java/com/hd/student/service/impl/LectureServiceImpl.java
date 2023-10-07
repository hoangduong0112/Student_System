package com.hd.student.service.impl;

import com.hd.student.entity.Lecture;
import com.hd.student.exception.ForeignKeyViolationException;
import com.hd.student.exception.ResourceNotFoundException;
import com.hd.student.payload.request.LectureRequest;
import com.hd.student.payload.response.ApiResponse;
import com.hd.student.payload.response.LectureResponse;
import com.hd.student.repository.LectureRepository;
import com.hd.student.service.LectureService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LectureServiceImpl implements LectureService {

    @Autowired
    private LectureRepository lectureRepository;


    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<LectureResponse> getAll(){
        return this.lectureRepository.findAll().stream().map((element)
                -> modelMapper.map(element, LectureResponse.class))
                .collect(Collectors.toList());
    }
    @Override
    public LectureResponse getById(int id){
        Lecture lecture = this.lectureRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Không tìm thấy giảng viên","id", id));
        return modelMapper.map(lecture, LectureResponse.class);
    }

    @Override
    public LectureResponse addNewLecture(LectureRequest rq){
        Lecture lecture = modelMapper.map(rq, Lecture.class);

        return modelMapper.map(this.lectureRepository.save(lecture), LectureResponse.class);
    }

    @Override
    public LectureResponse updateLecture(LectureRequest rq, int id){
        Lecture lecture;
//        lecture = this.lectureRepository.findById(id).orElseThrow(
//                ()->new ResourceNotFoundException("Không tìm thấy giảng viên", "id", id)
//        );
        if(this.lectureRepository.existsById(id)) {
            lecture = modelMapper.map(rq, Lecture.class);
            lecture.setId(id);
            return modelMapper.map(this.lectureRepository.save(lecture), LectureResponse.class);
        }else
            throw new ResourceNotFoundException("Không tìm thấy Giảng viên", "id", id);
    }

    @Override
    public ApiResponse deleteLecture(int id){
        Lecture lecture = this.lectureRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Không tìm thấy giảng viên", "id", id)
        );
        try{
            this.lectureRepository.delete(lecture);
            return new ApiResponse("Xóa thành công giảng viên", true);

        }catch (DataIntegrityViolationException ex){
            throw new ForeignKeyViolationException("Xóa thất bại do giảng viên đã có lịch dạy");
        }catch (Exception ex){
            return new ApiResponse("Xóa thất bại, có lỗi xảy ra", false);
        }
    }
}
