package com.hd.student.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SemesterDetailRequest {
    private int semesterUserId;
    private List<Integer> courseDataId;
}
