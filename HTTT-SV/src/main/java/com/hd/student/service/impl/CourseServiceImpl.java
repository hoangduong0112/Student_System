package com.hd.student.service.impl;

import com.hd.student.entity.Course;
import com.hd.student.exception.DataIntegrityViolationException;
import com.hd.student.exception.ResourceExistException;
import com.hd.student.exception.ResourceNotFoundException;
import com.hd.student.payload.request.CourseRequest;
import com.hd.student.payload.response.ApiResponse;
import com.hd.student.payload.response.CourseResponse;
import com.hd.student.repository.CourseRepository;
import com.hd.student.service.CourseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private ModelMapper modelMapper;

    public boolean checkExistName(String name){
        return courseRepository.existsByCourseNameLikeIgnoreCase(name);
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
    public CourseResponse addNewCourse(CourseRequest rq) {
        if (!this.checkExistName(rq.getCourseName())) {
            Course course = modelMapper.map(rq, Course.class);

            return modelMapper.map(this.courseRepository.save(course), CourseResponse.class);
        }
        else throw new ResourceExistException("Tên môn học", rq.getCourseName());
    }

    @Override
    public CourseResponse updateCourse(CourseRequest rq, int id) {
        if (!this.checkExistName(rq.getCourseName())) {
            Course course;
            course = this.courseRepository.findById(id).orElseThrow(
                    ()-> new ResourceNotFoundException("Không tìm thấy môn học", "id", id)
            );
            course.setId(id);
            course = modelMapper.map(rq, Course.class);
            return modelMapper.map(this.courseRepository.save(course), CourseResponse.class);
        }
        else throw new ResourceExistException("Tên môn học", rq.getCourseName());
    }

    @Override
    public ApiResponse deleteCourse(int id) {
        Course course = this.courseRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Không tìm thấy môn học này", "id",id)
        );
        try{
            this.courseRepository.delete(course);
            return new ApiResponse("Success", true);
        }catch (DataIntegrityViolationException ex){
            throw new DataIntegrityViolationException("Môn học đã được xếp giảng viên");
        }catch (Exception ex){
            return new ApiResponse("Success", true);
        }
    }
}
