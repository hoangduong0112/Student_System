package com.hd.student.service.impl;

import com.hd.student.entity.*;
import com.hd.student.exception.ResourceNotFoundException;
import com.hd.student.payload.request.SemesterDetailRequest;
import com.hd.student.payload.response.ApiResponse;
import com.hd.student.payload.response.SemesterDetailsResponse;
import com.hd.student.payload.response.SemesterUserResponse;
import com.hd.student.repository.CourseDatumRepository;
import com.hd.student.repository.SemesterDetailRepository;
import com.hd.student.repository.SemesterUserRepository;
import com.hd.student.service.SemesterDetailService;
import com.hd.student.service.SemesterUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SemesterDetailServiceImpl implements SemesterDetailService {


    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private SemesterDetailRepository semesterDetailRepository;

    @Autowired
    private SemesterUserRepository semesterUserRepository;
    @Autowired
    private CourseDatumRepository courseDatumRepository;

    @Override
    public List<SemesterDetailsResponse> getDetails(int id){
        List<SemesterDetail> semesterDetails = semesterDetailRepository.findBySemesterUser_Id(id);

        return semesterDetails.stream().map((element)
                -> modelMapper.map(element, SemesterDetailsResponse.class)).collect(Collectors.toList());
    }

    //chua the khai trien thanh chuc nang dang ky mon hoc
    @Override
    public ApiResponse addNewCourseInSemesterDetails(SemesterDetailRequest rq){
        SemesterUser semesterUser = this.semesterUserRepository.findById(rq.getSemesterUserId()).orElseThrow(
                () -> new ResourceNotFoundException("Không tìm thấy dữ liệu sinh viên với học kỳ này", "semesterUser","not found")
        );
        try {
            for (int id : rq.getCourseDataId()) {
                CourseDatum courseDatum = courseDatumRepository.findById(id).orElse(null);
                SemesterDetail sd = new SemesterDetail();

                sd.setCourseData(courseDatum);
                sd.setSemesterUser(semesterUser);
                sd.setIsPassed(true);
                sd.setScore(null);
                this.semesterDetailRepository.save(sd);
            }
        } catch (RuntimeException e) {
            throw new RuntimeException("Lỗi không xác định");
        }
        return new ApiResponse("Thêm thành công ", true);
    }

}
