package com.hd.student.payload.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseRequest {
    @NotNull
    private String courseName;
    @NotNull
    @Min(1)
    private Integer creditsNum;
    @NotNull
    private String note;
}
