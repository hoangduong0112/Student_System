package com.hd.student.service.impl;

import com.hd.student.entity.Course;
import com.hd.student.entity.CourseDatum;
import com.hd.student.entity.Lecture;
import com.hd.student.entity.ScheduleInfo;
import com.hd.student.exception.ForeignKeyViolationException;
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
                () -> new ResourceNotFoundException("Không tìm thấy môn học")
        );
        Lecture lecture = this.lectureRepository.findById(rq.getLectureId()).orElseThrow(
                () -> new ResourceNotFoundException("Không tìm thấy giảng viên")
        );

        Set<ScheduleInfo> scheduleInfos = new HashSet<>();
        for(int schedule: rq.getScheduleInfoId()) {
            ScheduleInfo scheduleInfo = this.scheduleInfoRepository.findById(schedule).orElseThrow(
                    ()->new ResourceNotFoundException("Không tìm thấy lịch học")
            );
            if(scheduleInfo.getCourseData()!= null)
                throw new ResourceExistException("Môn học đã được xếp lịch");
            else scheduleInfos.add(scheduleInfo);
        }
        try {
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
            // loi modelmapper khi co 2 mapping thuoc tinh id
            modelMapper.typeMap(CourseDatumRequest.class, CourseDatum.class)
                    .addMappings(mapper -> mapper.skip(CourseDatum::setId));
            CourseDatum courseDatum = modelMapper.map(rq, CourseDatum.class);
            courseDatum.setCourse(course);
            courseDatum.setLecture(lecture);
            for(ScheduleInfo schedule: scheduleInfos) {
                courseDatum.addScheduleInfo(schedule);
            }
            courseDatum.setIsEnded(false);
//            courseDatum.setScheduleInfos(scheduleInfos);
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
        CourseDatum courseDatum = this.courseDatumRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Không tìm thấy môn học mà bạn muốn chinh sua"));
        Course course = this.courseRepository.findById(rq.getCourseId()).orElseThrow(
                () -> new ResourceNotFoundException("Không tìm thấy môn học")
        );
        Lecture lecture = this.lectureRepository.findById(rq.getLectureId()).orElseThrow(
                () -> new ResourceNotFoundException("Không tìm thấy giảng viên")
        );

        Set<ScheduleInfo> scheduleInfos = new HashSet<>();
        for(int schedule: rq.getScheduleInfoId()) {
            ScheduleInfo scheduleInfo = this.scheduleInfoRepository.findById(schedule).orElseThrow(
                    ()->new ResourceNotFoundException("Không tìm thấy lịch học")
            );
            //Nếu lich đã được xếp trước hoặc lịch có id != id
            if(scheduleInfo.getCourseData()!= null && scheduleInfo.getCourseData().getId() != id)
                throw new ResourceExistException("Môn học đã được xếp lịch");
            else scheduleInfos.add(scheduleInfo);
        }

        try {
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
            courseDatum.getScheduleInfos().forEach(scheduleInfo -> scheduleInfo.setCourseData(null));
            courseDatum = modelMapper.map(rq, CourseDatum.class);
            courseDatum.setId(id);
            courseDatum.setCourse(course);
            courseDatum.setLecture(lecture);
            for(ScheduleInfo schedule: scheduleInfos) {
                courseDatum.addScheduleInfo(schedule);
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

    @Override
    public ApiResponse removeScheduleInfoByCourseDataId(int id){
        CourseDatum courseDatum = this.courseDatumRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Dữ liệu môn học không tìm thấy")
        );
        courseDatum.getScheduleInfos().forEach(scheduleInfo -> scheduleInfo.setCourseData(null));
        return new ApiResponse("Gỡ thành công thông tin lịch học", true);
    }

    @Override
    public ApiResponse deleteCourseData(int id){
        CourseDatum courseDatum = this.courseDatumRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("dữ liệu môn học không tìm thấy")
        );
        if(courseDatum.getSemesterDetails() != null)
            throw new ForeignKeyViolationException("Không thể xóa do dữ liệu đã được đăng ký");
        else{
            courseDatum.getScheduleInfos().forEach(scheduleInfo -> scheduleInfo.setCourseData(null));
            this.courseDatumRepository.delete(courseDatum);
        }

        return new ApiResponse("Gỡ thành công thông tin lịch học", true);
    }
}
