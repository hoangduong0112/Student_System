package com.hd.student.service.impl;

import com.hd.student.entity.Course;
import com.hd.student.exception.ForeignKeyViolationException;
import com.hd.student.exception.ResourceExistException;
import com.hd.student.exception.ResourceNotFoundException;
import com.hd.student.payload.request.CourseRequest;
import com.hd.student.payload.response.ApiResponse;
import com.hd.student.payload.response.CourseResponse;
import com.hd.student.repository.CourseRepository;
import com.hd.student.service.CourseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private ModelMapper modelMapper;

    public boolean checkExistName(String name, Integer id){
        Optional<Course> courseExist = courseRepository.findByCourseNameIgnoreCase(name);
        return courseExist.map(course->course.getId().equals(id)).orElse(true);
    }

    @Override
    public List<CourseResponse> getAllCourse(String search){
        List<Course> courses= new ArrayList<>();

        if(search != null)
            courses = this.courseRepository.findByCourseNameContainsIgnoreCase(search);
        else
            courses = this.courseRepository.findAll();
        return courses.stream().map((element) -> modelMapper.map(element, CourseResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public CourseResponse getCourseById(int id) {
        return this.courseRepository.findById(id).map((element) -> modelMapper.map(element, CourseResponse.class))
                .orElseThrow(()-> new ResourceNotFoundException("Không tìm thấy môn học"));
    }

    @Override
    public CourseResponse addNewCourse(CourseRequest rq) {
        if (checkExistName(rq.getCourseName(), null)) {
            Course course = modelMapper.map(rq, Course.class);

            return modelMapper.map(this.courseRepository.save(course), CourseResponse.class);
        }
        else throw new ResourceExistException("Tên phòng học đã tồn tại");
    }

    @Override
    public CourseResponse updateCourse(CourseRequest rq, int id) {
        if (checkExistName(rq.getCourseName(), id)) {
            Course course;
            course = this.courseRepository.findById(id).orElseThrow(
                    ()-> new ResourceNotFoundException("Không tìm thấy môn học")
            );
            course.setId(id);
            course = modelMapper.map(rq, Course.class);
            return modelMapper.map(this.courseRepository.save(course), CourseResponse.class);
        }
        else throw new ResourceExistException("Tên phòng học đã tồn tại");
    }

    @Override
    public ApiResponse deleteCourse(int id) {
        Course course = this.courseRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Không tìm thấy môn học này")
        );
        try{
            this.courseRepository.delete(course);
            return new ApiResponse("Success", true);
        }catch (DataIntegrityViolationException ex){
            throw new ForeignKeyViolationException("Môn học đã được xếp giảng viên");
        }catch (Exception ex){
            return new ApiResponse("False", true);
        }
    }
}
