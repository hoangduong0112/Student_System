package com.hd.student.service.impl;

import com.hd.student.entity.SemesterUser;
import com.hd.student.payload.response.SemesterDetailsResponse;
import com.hd.student.payload.response.SemesterUserResponse;
import com.hd.student.repository.SemesterDetailRepository;
import com.hd.student.service.SemesterDetailService;
import com.hd.student.service.SemesterUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SemesterDetailServiceImpl implements SemesterDetailService {


    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private SemesterDetailRepository semesterDetailRepository;
    @Autowired
    private SemesterUserService semesterUserService;


    @Override
    public List<SemesterDetailsResponse> getFromUserId(int id){
        List<SemesterUserResponse> semesterUsers = this.semesterUserService.getSemestersByUserId(id);

        List<SemesterDetailsResponse> rp;
        return null;

    }

}
