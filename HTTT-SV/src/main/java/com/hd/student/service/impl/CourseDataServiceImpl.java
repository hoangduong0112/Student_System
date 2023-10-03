package com.hd.student.service.impl;

import com.hd.student.entity.Course;
import com.hd.student.entity.CourseData;
import com.hd.student.entity.Lecture;
import com.hd.student.exception.ResourceNotFoundException;
import com.hd.student.payload.request.CourseDataRequest;
import com.hd.student.payload.response.ApiResponse;
import com.hd.student.payload.response.CourseDataResponse;
import com.hd.student.repository.CourseDataRepository;
import com.hd.student.repository.CourseRepository;
import com.hd.student.repository.LectureRepository;
import com.hd.student.service.CourseDataService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


    @Override
    public List<CourseDataResponse> getAll(String name) {
        modelMapper.typeMap(CourseData.class, CourseDataResponse.class).addMapping(
                CourseDatum -> CourseDatum.getCourse().getId(), CourseDataResponse::setCourseId
        );

        modelMapper.typeMap(CourseData.class, CourseDataResponse.class).addMapping(
                CourseDatum -> CourseDatum.getLecture().getId(), CourseDataResponse::setLectureId
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
    public ApiResponse addNewCourseData(CourseDataRequest rq) {
        Course course = this.courseRepository.findById(rq.getCourseId()).orElseThrow(
                () -> new ResourceNotFoundException("Không tìm thấy môn học", "id", rq.getCourseId())
        );
        Lecture lecture = this.lectureRepository.findById(rq.getLectureId()).orElseThrow(
                () -> new ResourceNotFoundException("Không tìm thấy giảng viên", "id", rq.getLectureId())
        );

        try {
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
            // loi modelmapper khi co 2 mapping thuoc tinh id
            modelMapper.typeMap(CourseDataRequest.class, CourseData.class)
                    .addMappings(mapper -> mapper.skip(CourseData::setId));
            CourseData courseData = modelMapper.map(rq, CourseData.class);
            courseData.setCourse(course);
            courseData.setLecture(lecture);

            this.courseDataRepository.save(courseData);
            return new ApiResponse("Success", true);
        } catch (Exception ex) {
            return new ApiResponse("Fail", false);
        }
    }

    @Override
    public ApiResponse updateCourseData(CourseDataRequest rq, int id) {
        Course course = this.courseRepository.findById(rq.getCourseId()).orElseThrow(
                () -> new ResourceNotFoundException("Không tìm thấy môn học", "id", rq.getCourseId())
        );
        Lecture lecture = this.lectureRepository.findById(rq.getLectureId()).orElseThrow(
                () -> new ResourceNotFoundException("Không tìm thấy giảng viên", "id", rq.getLectureId())
        );
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
        }
    }
}
