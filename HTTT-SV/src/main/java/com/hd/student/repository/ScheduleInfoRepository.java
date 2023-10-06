package com.hd.student.repository;

import com.hd.student.entity.CourseDatum;
import com.hd.student.entity.ScheduleInfo;
import com.hd.student.entity.Weekdays;
import com.hd.student.payload.response.ApiResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleInfoRepository extends JpaRepository<ScheduleInfo, Integer> {
    List<ScheduleInfo> findByCourseData_Id(Integer id);
    List<ScheduleInfo> findByWeekdaysAndStudyRoom_Id(Weekdays weekdays, Integer id);
}