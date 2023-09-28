package com.hd.student.serviceImpl;

import com.hd.student.repository.ScheduleInfoRepository;
import com.hd.student.service.ScheduleInfoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduleInfoServiceImpl implements ScheduleInfoService {

    @Autowired
    private ScheduleInfoRepository scheduleInfoRepository;


    @Autowired
    private ModelMapper modelMapper;

}
