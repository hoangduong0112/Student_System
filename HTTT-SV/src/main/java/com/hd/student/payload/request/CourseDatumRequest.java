package com.hd.student.payload.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hd.student.entity.Course;
import com.hd.student.entity.Lecture;
import com.hd.student.entity.ScheduleInfo;
import com.hd.student.payload.response.ScheduleInfoResponse;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    private LocalDate startDate;
    @NotNull
    private LocalDate endDate;
    @NotNull
    private Integer courseId;
    @NotNull
    private Integer lectureId;

    private Set<Integer> scheduleInfoId = new LinkedHashSet<>();
}
