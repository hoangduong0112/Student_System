package com.hd.student.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseDataResponse {
    private Integer id;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean isEnded;
    private Integer quantity;

    private Integer courseId;
    private Integer lectureId;
    private Set<ScheduleInfoResponse> scheduleInfos;

}
