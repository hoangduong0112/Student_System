package com.hd.student.service.impl;

import com.hd.student.entity.Course;
import com.hd.student.entity.CourseDatum;
import com.hd.student.entity.Lecture;
import com.hd.student.entity.ScheduleInfo;
import com.hd.student.exception.ResourceExistException;
import com.hd.student.exception.ResourceNotFoundException;
import com.hd.student.payload.request.CourseDatumRequest;
import com.hd.student.payload.response.ApiResponse;
import com.hd.student.payload.response.CourseDatumResponse;
import com.hd.student.repository.CourseDatumRepository;
import com.hd.student.repository.CourseRepository;
import com.hd.student.repository.LectureRepository;
import com.hd.student.repository.ScheduleInfoRepository;
import com.hd.student.service.CourseDatumService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CourseDatumServiceImpl implements CourseDatumService {

    @Autowired
    private CourseDatumRepository courseDatumRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private LectureRepository lectureRepository;
    @Autowired
    private ScheduleInfoRepository scheduleInfoRepository;


    @Override
    public List<CourseDatumResponse> getAll(String name) {
        modelMapper.typeMap(CourseDatum.class, CourseDatumResponse.class).addMapping(
                CourseDatum::getCourse, CourseDatumResponse::setCourse
        );

        modelMapper.typeMap(CourseDatum.class, CourseDatumResponse.class).addMapping(
                CourseDatum::getLecture, CourseDatumResponse::setLecture
        );

        List<CourseDatum> datas;
        if (name != null)
            datas = courseDatumRepository.findByCourse_CourseNameContainsIgnoreCase(name);
        else
            datas = courseDatumRepository.findAll();
        return datas.stream().map((element)
                -> modelMapper.map(element, CourseDatumResponse.class)).collect(Collectors.toList());
    }

    @Override
    public CourseDatumResponse addNewCourseData(CourseDatumRequest rq) {
        Course course = this.courseRepository.findById(rq.getCourseId()).orElseThrow(
                () -> new ResourceNotFoundException("Không tìm thấy môn học", "id", rq.getCourseId())
        );
        Lecture lecture = this.lectureRepository.findById(rq.getLectureId()).orElseThrow(
                () -> new ResourceNotFoundException("Không tìm thấy giảng viên", "id", rq.getLectureId())
        );

        for(int schedule: rq.getScheduleInfoId()) {
            ScheduleInfo scheduleInfo = this.scheduleInfoRepository.findById(schedule).orElseThrow(
                    ()->new ResourceNotFoundException("Không tìm thấy lịch học", "id", schedule)
            );
            if(scheduleInfo.getCourseData()!= null)
                throw new ResourceExistException("Lịch học", scheduleInfo.getId().toString());
        }
        try {
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
            // loi modelmapper khi co 2 mapping thuoc tinh id
            modelMapper.typeMap(CourseDatumRequest.class, CourseDatum.class)
                    .addMappings(mapper -> mapper.skip(CourseDatum::setId));
            CourseDatum courseDatum = modelMapper.map(rq, CourseDatum.class);
            courseDatum.setCourse(course);
            courseDatum.setLecture(lecture);
//            Set<ScheduleInfo> scheduleInfos = new HashSet<>();
            for(int schedule: rq.getScheduleInfoId()) {
//                scheduleInfos.add(this.scheduleInfoRepository.findById(schedule).orElseThrow(
//                        ()-> new ResourceNotFoundException("Không tìm thấy lịch hoc", "id", schedule)
//                ));

                courseDatum.addScheduleInfo(this.scheduleInfoRepository.findById(schedule).orElseThrow(
                        ()->new ResourceNotFoundException("lịch học", "id", schedule)
                ));
            }
            courseDatum.setIsEnded(false);
//            courseDatum.setScheduleInfos(scheduleInfos);
            //Didn't work with relationship
            courseDatum = this.courseDatumRepository.save(courseDatum);

            return modelMapper.map(courseDatum, CourseDatumResponse.class);
        } catch (ResourceNotFoundException ex) {
            throw ex;
        }catch (RuntimeException ex) {
            throw new RuntimeException("Có lỗi xảy ra");
        }
    }

    @Override
    @Transactional
    public CourseDatumResponse updateCourseData(CourseDatumRequest rq, int id) {
        Course course = this.courseRepository.findById(rq.getCourseId()).orElseThrow(
                () -> new ResourceNotFoundException("Không tìm thấy môn học", "id", rq.getCourseId())
        );
        Lecture lecture = this.lectureRepository.findById(rq.getLectureId()).orElseThrow(
                () -> new ResourceNotFoundException("Không tìm thấy giảng viên", "id", rq.getLectureId())
        );

        List<ScheduleInfo> sc = scheduleInfoRepository.findByCourseData_Id(id);
        for(ScheduleInfo scheduleInfo: sc){
            scheduleInfo.setCourseData(null);
        }
        this.courseDatumRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Không tìm thấy môn học mà bạn muốn chinh sua", "id", id));
        CourseDatum courseDatum;
        try {
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

            courseDatum = modelMapper.map(rq, CourseDatum.class);
            courseDatum.setId(id);
            courseDatum.setCourse(course);
            courseDatum.setLecture(lecture);
            for(int schedule: rq.getScheduleInfoId()) {
                courseDatum.addScheduleInfo(this.scheduleInfoRepository.findById(schedule).orElseThrow(
                        ()->new ResourceNotFoundException("Không tìm thấy lịch học", "id", schedule)
                ));
            }
            courseDatum.setIsEnded(false);

            courseDatum = this.courseDatumRepository.save(courseDatum);

            return modelMapper.map(courseDatum, CourseDatumResponse.class);
        } catch (ResourceNotFoundException ex) {
            throw ex;
        }catch (RuntimeException ex) {
            throw new RuntimeException("Có lỗi xảy ra");
        }
    }
}
