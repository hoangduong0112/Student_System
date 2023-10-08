package com.hd.student.service.impl;

import com.hd.student.entity.SemesterDetail;
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
import java.util.stream.Collectors;

@Service
public class SemesterDetailServiceImpl implements SemesterDetailService {


    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private SemesterDetailRepository semesterDetailRepository;


    @Override
    public List<SemesterDetailsResponse> getDetails(int id){
        List<SemesterDetail> semesterDetails = semesterDetailRepository.findBySemesterUser_Id(id);

        return semesterDetails.stream().map((element)
                -> modelMapper.map(element, SemesterDetailsResponse.class)).collect(Collectors.toList());
    }

}
