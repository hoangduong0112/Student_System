package com.hd.student.service.impl;

import com.hd.student.entity.Course;
import com.hd.student.entity.CourseDatum;
import com.hd.student.entity.Lecture;
import com.hd.student.exception.ResourceNotFoundException;
import com.hd.student.payload.request.CourseDatumRequest;
import com.hd.student.payload.response.ApiResponse;
import com.hd.student.payload.response.CourseDatumResponse;
import com.hd.student.repository.CourseDatumRepository;
import com.hd.student.repository.CourseRepository;
import com.hd.student.repository.LectureRepository;
<<<<<<< HEAD:HTTT-SV/src/main/java/com/hd/student/service/impl/CourseDatumServiceImpl.java
import com.hd.student.repository.ScheduleInfoRepository;
import com.hd.student.service.CourseDatumService;
=======
import com.hd.student.service.CourseDataService;
>>>>>>> parent of 45da031 (forked):HTTT-SV/src/main/java/com/hd/student/service/impl/CourseDataServiceImpl.java
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
<<<<<<< HEAD:HTTT-SV/src/main/java/com/hd/student/service/impl/CourseDatumServiceImpl.java
    public CourseDatumResponse addNewCourseData(CourseDatumRequest rq) {
=======
    public ApiResponse addNewCourseData(CourseDataRequest rq) {
>>>>>>> parent of 45da031 (forked):HTTT-SV/src/main/java/com/hd/student/service/impl/CourseDataServiceImpl.java
        Course course = this.courseRepository.findById(rq.getCourseId()).orElseThrow(
                () -> new ResourceNotFoundException("Không tìm thấy môn học", "id", rq.getCourseId())
        );
        Lecture lecture = this.lectureRepository.findById(rq.getLectureId()).orElseThrow(
                () -> new ResourceNotFoundException("Không tìm thấy giảng viên", "id", rq.getLectureId())
        );

        try {
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
            // loi modelmapper khi co 2 mapping thuoc tinh id
<<<<<<< HEAD:HTTT-SV/src/main/java/com/hd/student/service/impl/CourseDatumServiceImpl.java
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
=======
            modelMapper.typeMap(CourseDataRequest.class, CourseData.class)
                    .addMappings(mapper -> mapper.skip(CourseData::setId));
            CourseData courseData = modelMapper.map(rq, CourseData.class);
            courseData.setCourse(course);
            courseData.setLecture(lecture);

            this.courseDataRepository.save(courseData);
            return new ApiResponse("Success", true);
        } catch (Exception ex) {
            return new ApiResponse("Fail", false);
>>>>>>> parent of 45da031 (forked):HTTT-SV/src/main/java/com/hd/student/service/impl/CourseDataServiceImpl.java
        }
    }

    @Override
<<<<<<< HEAD:HTTT-SV/src/main/java/com/hd/student/service/impl/CourseDatumServiceImpl.java
    @Transactional
    public CourseDatumResponse updateCourseData(CourseDatumRequest rq, int id) {
=======
    public ApiResponse updateCourseData(CourseDataRequest rq, int id) {
>>>>>>> parent of 45da031 (forked):HTTT-SV/src/main/java/com/hd/student/service/impl/CourseDataServiceImpl.java
        Course course = this.courseRepository.findById(rq.getCourseId()).orElseThrow(
                () -> new ResourceNotFoundException("Không tìm thấy môn học", "id", rq.getCourseId())
        );
        Lecture lecture = this.lectureRepository.findById(rq.getLectureId()).orElseThrow(
                () -> new ResourceNotFoundException("Không tìm thấy giảng viên", "id", rq.getLectureId())
        );
<<<<<<< HEAD:HTTT-SV/src/main/java/com/hd/student/service/impl/CourseDatumServiceImpl.java

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
=======
        this.courseDataRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Không tìm thấy môn học mà bạn muốn chinh sua", "id", id)
        );

        try {
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

            CourseData courseData = modelMapper.map(rq, CourseData.class);
            courseData.setId(id);
            courseData.setCourse(course);
            courseData.setLecture(lecture);

            this.courseDataRepository.save(courseData);
            return new ApiResponse("Success", true);
        } catch (Exception ex) {
            return new ApiResponse("Fail", false);
>>>>>>> parent of 45da031 (forked):HTTT-SV/src/main/java/com/hd/student/service/impl/CourseDataServiceImpl.java
        }
    }
}
