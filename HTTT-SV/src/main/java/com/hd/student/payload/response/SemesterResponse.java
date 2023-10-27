package com.hd.student.payload.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SemesterResponse {
    private Integer id;
    private String semesterName;
    private String note;
    private boolean isFinish;
}
