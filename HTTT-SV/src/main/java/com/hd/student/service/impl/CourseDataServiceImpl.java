package com.hd.student.service.impl;

import com.hd.student.entity.Course;
import com.hd.student.entity.CourseData;
import com.hd.student.entity.Lecture;
import com.hd.student.entity.ScheduleInfo;
import com.hd.student.exception.ResourceExistException;
import com.hd.student.exception.ResourceNotFoundException;
import com.hd.student.payload.request.CourseDataRequest;
import com.hd.student.payload.response.ApiResponse;
import com.hd.student.payload.response.CourseDataResponse;
import com.hd.student.repository.CourseDataRepository;
import com.hd.student.repository.CourseRepository;
import com.hd.student.repository.LectureRepository;
import com.hd.student.service.CourseDataService;
import com.hd.student.repository.ScheduleInfoRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseDataServiceImpl implements CourseDataService {

    @Autowired
    private CourseDataRepository courseDataRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private LectureRepository lectureRepository;
    @Autowired
    private ScheduleInfoRepository scheduleInfoRepository;


    @Override
    public List<CourseDataResponse> getAll(String name) {
        modelMapper.typeMap(CourseData.class, CourseDataResponse.class).addMapping(
                CourseData::getCourse, CourseDataResponse::setCourse
        );

        modelMapper.typeMap(CourseData.class, CourseDataResponse.class).addMapping(
                CourseData::getLecture, CourseDataResponse::setLecture
        );

        List<CourseData> datas;
        if (name != null)
            datas = courseDataRepository.findByCourse_CourseNameContainsIgnoreCase(name);
        else
            datas = courseDataRepository.findAll();
        return datas.stream().map((element)
                -> modelMapper.map(element, CourseDataResponse.class)).collect(Collectors.toList());
    }

    @Override
    public CourseDataResponse addNewCourseData(CourseDataRequest rq) {
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
            modelMapper.typeMap(CourseDataRequest.class, CourseData.class)
                    .addMappings(mapper -> mapper.skip(CourseData::setId));
            CourseData courseData = modelMapper.map(rq, CourseData.class);
            courseData.setCourse(course);
            courseData.setLecture(lecture);
//            Set<ScheduleInfo> scheduleInfos = new HashSet<>();
            for(int schedule: rq.getScheduleInfoId()) {
//                scheduleInfos.add(this.scheduleInfoRepository.findById(schedule).orElseThrow(
//                        ()-> new ResourceNotFoundException("Không tìm thấy lịch hoc", "id", schedule)
//                ));

                courseData.addScheduleInfo(this.scheduleInfoRepository.findById(schedule).orElseThrow(
                        ()->new ResourceNotFoundException("lịch học", "id", schedule)
                ));
            }
            courseData.setIsEnded(false);
//            courseData.setScheduleInfos(scheduleInfos);
            //Didn't work with relationship
            courseData = this.courseDataRepository.save(courseData);

            return modelMapper.map(courseData, CourseDataResponse.class);
        } catch (ResourceNotFoundException ex) {
            throw ex;
        }catch (RuntimeException ex) {
            throw new RuntimeException("Có lỗi xảy ra");
        }
    }

    @Override
    @Transactional
    public CourseDataResponse updateCourseData(CourseDataRequest rq, int id) {
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
        this.courseDataRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Không tìm thấy môn học mà bạn muốn chinh sua", "id", id));
        CourseData courseData;
        try {
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

            courseData = modelMapper.map(rq, CourseData.class);
            courseData.setId(id);
            courseData.setCourse(course);
            courseData.setLecture(lecture);
            for(int schedule: rq.getScheduleInfoId()) {
                courseData.addScheduleInfo(this.scheduleInfoRepository.findById(schedule).orElseThrow(
                        ()->new ResourceNotFoundException("Không tìm thấy lịch học", "id", schedule)
                ));
            }
            courseData.setIsEnded(false);

            courseData = this.courseDataRepository.save(courseData);

            return modelMapper.map(courseData, CourseDataResponse.class);
        } catch (ResourceNotFoundException ex) {
            throw ex;
        }catch (RuntimeException ex) {
            throw new RuntimeException("Có lỗi xảy ra");
        }
    }
}
