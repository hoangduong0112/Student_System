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
import com.hd.student.service.CourseDatumService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
                CourseDatum -> CourseDatum.getCourse(), CourseDatumResponse::setCourse
        );

        modelMapper.typeMap(CourseDatum.class, CourseDatumResponse.class).addMapping(
                CourseDatum -> CourseDatum.getLecture(), CourseDatumResponse::setLecture
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
    public ApiResponse addNewCourseData(CourseDatumRequest rq) {
        Course course = this.courseRepository.findById(rq.getCourseId()).orElseThrow(
                () -> new ResourceNotFoundException("Không tìm thấy môn học", "id", rq.getCourseId())
        );
        Lecture lecture = this.lectureRepository.findById(rq.getLectureId()).orElseThrow(
                () -> new ResourceNotFoundException("Không tìm thấy giảng viên", "id", rq.getLectureId())
        );

        try {
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
            // loi modelmapper khi co 2 mapping thuoc tinh id
            modelMapper.typeMap(CourseDatumRequest.class, CourseDatum.class)
                    .addMappings(mapper -> mapper.skip(CourseDatum::setId));
            CourseDatum courseDatum = modelMapper.map(rq, CourseDatum.class);
            courseDatum.setCourse(course);
            courseDatum.setLecture(lecture);

            this.courseDatumRepository.save(courseDatum);
            return new ApiResponse("Success", true);
        } catch (Exception ex) {
            return new ApiResponse("Fail", false);
        }
    }

    @Override
    public ApiResponse updateCourseData(CourseDatumRequest rq, int id) {
        Course course = this.courseRepository.findById(rq.getCourseId()).orElseThrow(
                () -> new ResourceNotFoundException("Không tìm thấy môn học", "id", rq.getCourseId())
        );
        Lecture lecture = this.lectureRepository.findById(rq.getLectureId()).orElseThrow(
                () -> new ResourceNotFoundException("Không tìm thấy giảng viên", "id", rq.getLectureId())
        );
        this.courseDatumRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Không tìm thấy môn học mà bạn muốn chinh sua", "id", id)
        );

        try {
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

            CourseDatum courseDatum = modelMapper.map(rq, CourseDatum.class);
            courseDatum.setId(id);
            courseDatum.setCourse(course);
            courseDatum.setLecture(lecture);

            this.courseDatumRepository.save(courseDatum);
            return new ApiResponse("Success", true);
        } catch (Exception ex) {
            return new ApiResponse("Fail", false);
        }
    }
}
