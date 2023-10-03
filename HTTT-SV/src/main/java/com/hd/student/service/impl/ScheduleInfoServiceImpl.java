package com.hd.student.service.impl;

import com.hd.student.entity.ScheduleInfo;
import com.hd.student.entity.Weekdays;
import com.hd.student.exception.ResourceNotFoundException;
import com.hd.student.payload.request.ScheduleInfoRequest;
import com.hd.student.payload.response.ScheduleInfoResponse;
import com.hd.student.repository.ScheduleInfoRepository;
import com.hd.student.service.ScheduleInfoService;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleInfoServiceImpl implements ScheduleInfoService {

    @Autowired
    private ScheduleInfoRepository scheduleInfoRepository;


    @Autowired
    private ModelMapper modelMapper;


    public List<ScheduleInfoResponse> getAll(){
        Converter<Weekdays, String> weekdaysStringConverter = ctx ->
                ctx.getSource() == null ? null : ctx.getSource().name();
        modelMapper.addConverter(weekdaysStringConverter);
        modelMapper.typeMap(ScheduleInfo.class, ScheduleInfoResponse.class).addMapping(
                ScheduleInfo ->ScheduleInfo.getStudyRoom().getStudyRoomName(),ScheduleInfoResponse::setStudyRoom
        );
        return this.scheduleInfoRepository.findAll().stream().map((element)
                -> modelMapper.map(element, ScheduleInfoResponse.class)).collect(Collectors.toList());
    }

    public ScheduleInfoResponse getById(int id){
        Converter<Weekdays, String> weekdaysStringConverter = ctx ->
                ctx.getSource() == null ? null : ctx.getSource().name();
        modelMapper.addConverter(weekdaysStringConverter);
        modelMapper.typeMap(ScheduleInfo.class, ScheduleInfoResponse.class).addMapping(
                ScheduleInfo ->ScheduleInfo.getStudyRoom().getStudyRoomName(),ScheduleInfoResponse::setStudyRoom
        );
        ScheduleInfo info = this.scheduleInfoRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Không tồn tại lịch", "id", id));
        return modelMapper.map(info,ScheduleInfoResponse.class);
    }


}
