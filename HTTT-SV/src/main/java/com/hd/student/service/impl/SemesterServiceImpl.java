package com.hd.student.service.impl;

import com.hd.student.entity.Semester;
import com.hd.student.exception.ResourceNotFoundException;
import com.hd.student.exception.ForeignKeyViolationException;
import com.hd.student.payload.request.SemesterRequest;
import com.hd.student.payload.response.ApiResponse;
import com.hd.student.payload.response.SemesterResponse;
import com.hd.student.repository.SemesterRepository;
import com.hd.student.service.SemesterService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
    public List<SemesterResponse> getAvailable() {
        return semesterRepository.findByIsFinish(false).stream().map((element)
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
        Semester semester = semesterRepository.findById(id).orElseThrow(()->
             new ResourceNotFoundException("Không tìm thấy học kỳ", "id", id));
        boolean isFinish = semester.getIsFinish();
        semester = modelMapper.map(rq, Semester.class);

        semester.setId(id);
        semester.setIsFinish(isFinish);
        semester = this.semesterRepository.save(semester);
        return modelMapper.map(semester, SemesterResponse.class);
    }
    @Override
    public ApiResponse deleteSemesterById(int id){
        Semester semester = this.semesterRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Không tìm thấy học kỳ", "id", id)
        );
//        if(semester.getIsFinish()){
//            return new ApiResponse("Xóa thất bại vì học kì đã kết thúc", false);
//        }
        try {
            this.semesterRepository.delete(semester);
            return new ApiResponse("Xóa thành công", true);
        }catch (DataIntegrityViolationException ex){
            throw new ForeignKeyViolationException("Học kỳ đã có dữ liệu");
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

