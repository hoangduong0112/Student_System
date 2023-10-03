package com.hd.student.service.impl;

import com.hd.student.entity.Semester;
import com.hd.student.exception.DataIntegrityViolationException;
import com.hd.student.exception.ResourceExistException;
import com.hd.student.exception.ResourceNotFoundException;
import com.hd.student.payload.request.SemesterRequest;
import com.hd.student.payload.response.ApiResponse;
import com.hd.student.payload.response.SemesterResponse;
import com.hd.student.repository.SemesterRepository;
import com.hd.student.service.SemesterService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SemesterServiceImpl implements SemesterService {

    @Autowired
    private SemesterRepository semesterRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<SemesterResponse> getAll() {
        return this.semesterRepository.findAll().stream().map((element)
                        -> modelMapper.map(element, SemesterResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public SemesterResponse getSemesterById(int id){
        Semester semester = this.semesterRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Không tìm thấy học kỳ", "id", id)
        );
        return modelMapper.map(semester, SemesterResponse.class);
    }

    @Override
    public SemesterResponse addSemester(SemesterRequest rq){
        Semester semester = modelMapper.map(rq, Semester.class);
        semester.setIsFinish(false);
        semester = this.semesterRepository.save(semester);

        return modelMapper.map(semester, SemesterResponse.class);
    }
    @Override
    public SemesterResponse updateSemester(SemesterRequest rq, int id){
        if(semesterRepository.existsBySemesterNameIgnoreCase(rq.getSemesterName()))
            throw new ResourceExistException("Đã tồn tại học kỳ này", rq.getSemesterName());
        if(!semesterRepository.existsById(id))
            throw new ResourceNotFoundException("Không tìm thấy học kỳ", "id", id);

        Semester semester = modelMapper.map(rq, Semester.class);
        semester.setId(id);
        semester = this.semesterRepository.save(semester);
        return modelMapper.map(semester, SemesterResponse.class);
    }
    @Override
    public ApiResponse deleteSemesterById(int id){
        Semester semester = this.semesterRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Không tìm thấy học kỳ", "id", id)
        );
        try {
            this.semesterRepository.delete(semester);
            return new ApiResponse("Xóa thành công", true);
        }catch (DataIntegrityViolationException ex){
            throw new DataIntegrityViolationException("Học kỳ đã có dữ liệu");
        }catch(Exception ex){
            throw new RuntimeException("Xóa thất bại vì lỗi server");
        }
    }

    @Override
    public SemesterResponse setFinish(int id){
        Semester semester = this.semesterRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Không tìm thấy học kỳ", "id", id)
        );
        semester.setIsFinish(true);
        semester = this.semesterRepository.save(semester);
        return modelMapper.map(semester, SemesterResponse.class);
    }
}

