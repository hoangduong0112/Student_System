package com.hd.student.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudyRoomResponse {
    private Integer id;
    private String studyRoomName;
    private Boolean isAvailable;
}
