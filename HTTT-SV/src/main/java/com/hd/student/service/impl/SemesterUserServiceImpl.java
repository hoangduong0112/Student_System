package com.hd.student.service.impl;

import com.hd.student.entity.Semester;
import com.hd.student.entity.SemesterUser;
import com.hd.student.payload.response.SemesterResponse;
import com.hd.student.repository.SemesterUserRepository;
import com.hd.student.service.SemesterUserService;
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


    @Override
    public List<SemesterResponse> getSemestersByUserId(Integer userId) {
        List<SemesterUser> semesterUsers = semesterUserRepository.findAllByUserId(userId);
        List<SemesterResponse> semesterResponses = new ArrayList<>();

        for (SemesterUser semesterUser : semesterUsers) {
            Semester semester = semesterUser.getSemester();
            SemesterResponse semesterResponse = modelMapper.map(semester, SemesterResponse.class);
            semesterResponses.add(semesterResponse);
        }

        return semesterResponses;
    }
}

