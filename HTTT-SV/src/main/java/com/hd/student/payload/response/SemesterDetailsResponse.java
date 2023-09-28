package com.hd.student.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SemesterDetailsResponse {
    private Integer id;
    private Double score;

    private Boolean isPassed;
    private String subjectName;
    private String lectureName;
    private LocalDate startDate;
    private LocalDate endDate;

    private String groupName;
}
