package com.hd.student.service.impl;

import com.hd.student.repository.SemesterDetailRepository;
import com.hd.student.service.SemesterDetailService;
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


//    @Override
//    public List<SemesterDetailsResponse> getSemeterDetailsBySemeterStudentId(Integer semeterStudentId) {
//        List<SemesterDetail> semeterDetails = this.semesterDetailRepository.(semeterStudentId);
//
//        List<SemesterDetailsResponse> rp = new ArrayList<>();
//        for (SemesterDetail semeterDetail : semeterDetails) {
//            SemesterDetailsResponse data = modelMapper.map(semeterDetail, SemesterDetailsResponse.class);
//            rp.add(data);
//        }
//        return rp;
//    }

}
