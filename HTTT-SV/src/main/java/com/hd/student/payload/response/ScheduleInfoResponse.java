package com.hd.student.payload.response;

import com.hd.student.entity.Weekdays;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleInfoResponse {
    private Integer id;
    private String weekdays;
    private Integer startAt;
    private Integer endAt;
    private StudyRoomResponse studyRoom;

}
