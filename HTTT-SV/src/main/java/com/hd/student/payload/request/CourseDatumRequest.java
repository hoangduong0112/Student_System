package com.hd.student.payload.request;

import com.hd.student.entity.Course;
import com.hd.student.entity.Lecture;
import com.hd.student.entity.ScheduleInfo;
import com.hd.student.payload.response.ScheduleInfoResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseDatumRequest {
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate startDate;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate endDate;

    private Integer courseId;
    private Integer lectureId;

    private Set<Integer> scheduleInfoId = new LinkedHashSet<>();
}
