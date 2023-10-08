package com.hd.student.service.impl;

import com.hd.student.entity.Semester;
import com.hd.student.entity.SemesterStatusEnum;
import com.hd.student.entity.SemesterUser;
import com.hd.student.entity.User;
import com.hd.student.exception.ResourceExistException;
import com.hd.student.exception.ResourceNotFoundException;
import com.hd.student.payload.request.SemesterUserRequest;
import com.hd.student.payload.response.ApiResponse;
import com.hd.student.payload.response.SemesterUserResponse;
import com.hd.student.repository.SemesterRepository;
import com.hd.student.repository.SemesterUserRepository;
import com.hd.student.repository.UserRepository;
import com.hd.student.service.SemesterUserService;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SemesterUserServiceImpl implements SemesterUserService {


    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private SemesterUserRepository semesterUserRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SemesterRepository semesterRepository;


    @Override
    public List<SemesterUserResponse> getSemestersByUserId(Integer userId) {
        List<SemesterUser> semesterUsers = semesterUserRepository.findAllByUserId(userId);
        modelMapper.typeMap(SemesterUser.class, SemesterUserResponse.class).addMapping(
                SemesterUser -> SemesterUser.getSemester().getSemesterName(), SemesterUserResponse::setSemesterName
        );
        Converter<SemesterStatusEnum, String> statusConverter = ctx ->
                ctx.getSource() == null ? null : ctx.getSource().name();
        modelMapper.addConverter(statusConverter);
        List<SemesterUserResponse> rp = new ArrayList<>();

        for (SemesterUser semesterUser : semesterUsers) {
            SemesterUserResponse response = modelMapper.map(semesterUser, SemesterUserResponse.class);
            rp.add(response);
        }

        return rp;
    }

    @Override
    public ApiResponse addSemesterForUser(SemesterUserRequest rq) {
        Semester semester = this.semesterRepository.findById(rq.getSemesterId()).orElseThrow(
                () -> new ResourceNotFoundException("Không tìm thấy học kỳ", "học kỳ", rq.getSemesterId())
        );
        try {
            for (int id : rq.getUserId()) {
                if (!semester.getIsFinish()) {
                    if (semesterUserRepository.existsBySemester_IdAndUser_Id(semester.getId(), id)) {
                        User user = userRepository.findById(id).orElse(null);
                        SemesterUser su = new SemesterUser();

                        su.setUser(user);
                        su.setSemester(semester);
                        su.setStatus(SemesterStatusEnum.WAITING);
                        this.semesterUserRepository.save(su);
                    }
                }
                else throw new ResourceExistException("Hoc kỳ đã kết thúc", semester.getSemesterName());
            }
        }catch (ResourceExistException e) {
            throw e;
        } catch (RuntimeException e) {
            throw new RuntimeException("Lỗi không xác định");
        }
        return new ApiResponse("Thêm thành công ", true);
    }
}

