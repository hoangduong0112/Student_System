package com.hd.student.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TranscriptResponse {
    private Integer id;
    private String language;
    private SemesterResponse fromSemester;
    private SemesterResponse toSemester;

    private Integer quantity;

    private String contactPhone;

    private Boolean isSealed;

    private int onlineServiceId;
}
