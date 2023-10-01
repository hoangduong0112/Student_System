package com.hd.student.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentResponse {
    private int id;
    private String departmentName;
    private String description;

    private List<MajorResponse> majors;
}
