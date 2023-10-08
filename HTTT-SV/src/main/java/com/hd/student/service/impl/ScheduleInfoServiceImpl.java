package com.hd.student.service.impl;

import com.hd.student.entity.ScheduleInfo;
import com.hd.student.entity.StudyRoom;
import com.hd.student.entity.Weekdays;
import com.hd.student.exception.ConflictException;
import com.hd.student.exception.EnumNotFoundException;
import com.hd.student.exception.ResourceNotFoundException;
import com.hd.student.payload.request.ScheduleInfoRequest;
import com.hd.student.payload.response.ApiResponse;
import com.hd.student.payload.response.ScheduleInfoResponse;
import com.hd.student.repository.ScheduleInfoRepository;
import com.hd.student.repository.StudyRoomRepository;
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
    @Autowired
    private StudyRoomRepository studyRoomRepository;


    @Override
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

    @Override
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

    private boolean isTimeSlotConflict(ScheduleInfo si) {
        boolean[] timeSlots = new boolean[16];
        List<ScheduleInfo> scheduleInfos = scheduleInfoRepository
                .findByWeekdaysAndStudyRoom_Id(si.getWeekdays(), si.getStudyRoom().getId());
        //danh sach cac tiet hoc cung 1 thu va 1 phong hoc
        for (ScheduleInfo scheduleInfo : scheduleInfos) {
            for (int i = scheduleInfo.getStartAt(); i < scheduleInfo.getStartAt() + scheduleInfo.getEndAt(); i++) {
                timeSlots[i - 1] = true;  // Đánh dấu tiết học đã được sử dụng
            }
        }
        //bat dau kiem tra
        for(int i = si.getStartAt(); i < si.getStartAt() + si.getEndAt(); i++)
            if(timeSlots[i - 1])
                return true;

        return false;
    }

    @Override
    public ScheduleInfoResponse addScheduleInfo(ScheduleInfoRequest rq){
        Converter<Weekdays, String> weekdaysStringConverter = ctx ->
                ctx.getSource() == null ? null : ctx.getSource().name();
        modelMapper.addConverter(weekdaysStringConverter);
        modelMapper.typeMap(ScheduleInfo.class, ScheduleInfoResponse.class).addMapping(
                ScheduleInfo ->ScheduleInfo.getStudyRoom().getStudyRoomName(),ScheduleInfoResponse::setStudyRoom
        );
        ScheduleInfo si = modelMapper.map(rq, ScheduleInfo.class);
        if(si.getWeekdays() != null){
        StudyRoom st = this.studyRoomRepository.findById(rq.getStudyRoom()).orElseThrow(
                () -> new ResourceNotFoundException("Không tìm thấy phòng học", "id", rq.getStudyRoom()));
        si.setStudyRoom(st);
        if(!this.isTimeSlotConflict(si)){
            return modelMapper.map(this.scheduleInfoRepository.save(si), ScheduleInfoResponse.class);
        }
        else
            throw new ConflictException("Trùng tiết học trong buổi học và phòng học này");
        }
        throw new EnumNotFoundException("Buổi học",rq.getWeekdays());
    }

    @Override
    public ScheduleInfoResponse updateScheduleInfo(ScheduleInfoRequest rq, int id){
        if(!this.scheduleInfoRepository.existsById(id))
            throw new ResourceNotFoundException("Không tìm thấy thông tin buổi học", "id", id);
        Converter<Weekdays, String> weekdaysStringConverter = ctx ->
                ctx.getSource() == null ? null : ctx.getSource().name();
        modelMapper.addConverter(weekdaysStringConverter);
        modelMapper.typeMap(ScheduleInfo.class, ScheduleInfoResponse.class).addMapping(
                ScheduleInfo ->ScheduleInfo.getStudyRoom().getStudyRoomName(),ScheduleInfoResponse::setStudyRoom
        );
        ScheduleInfo si = modelMapper.map(rq, ScheduleInfo.class);
        StudyRoom st = this.studyRoomRepository.findById(rq.getStudyRoom()).orElseThrow(
                () -> new ResourceNotFoundException("Không tìm thấy phòng học", "id", rq.getStudyRoom()));
        si.setStudyRoom(st);
        si.setId(id);
        if(!this.isTimeSlotConflict(si)){
            return modelMapper.map(this.scheduleInfoRepository.save(si), ScheduleInfoResponse.class);
        }
        else
            throw new ConflictException("Trùng tiết học trong buổi học và phòng học này");
    }
    @Override
    public ApiResponse deleteSchedule(int id){
        ScheduleInfo si = this.scheduleInfoRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Không tìm thấy thông tin buổi học", "id", id)
        );
        this.scheduleInfoRepository.delete(si);
        return new ApiResponse("Xóa thành công", true);
    }
}
