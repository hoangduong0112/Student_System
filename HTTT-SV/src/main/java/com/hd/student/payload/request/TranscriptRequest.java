package com.hd.student.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TranscriptRequest {
    private String language;
    private Integer fromSemester;
    private Integer toSemester;

    private Integer quantity;

    private String contactPhone;

    private Boolean isSealed;
}