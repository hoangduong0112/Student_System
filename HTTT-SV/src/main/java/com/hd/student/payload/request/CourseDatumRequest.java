package com.hd.student.payload.request;

import java.time.LocalDate;

public class CourseDatumRequest {
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean isEnded;
    private Integer quantity;

    private String courseName;
    private String lectureName;
}
