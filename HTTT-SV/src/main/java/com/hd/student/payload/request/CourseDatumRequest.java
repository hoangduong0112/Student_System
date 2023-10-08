package com.hd.student.payload.request;

import com.hd.student.payload.response.ScheduleInfoResponse;
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
<<<<<<< HEAD:HTTT-SV/src/main/java/com/hd/student/payload/request/CourseDatumRequest.java
public class CourseDatumRequest {
    @DateTimeFormat(pattern = "dd-MM-yyyy")
=======
public class CourseDataRequest {
>>>>>>> parent of 45da031 (forked):HTTT-SV/src/main/java/com/hd/student/payload/request/CourseDataRequest.java
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean isEnded;
    private Integer quantity;

    private Integer courseId;
    private Integer lectureId;

    private Set<ScheduleInfoResponse> scheduleInfos;
}
