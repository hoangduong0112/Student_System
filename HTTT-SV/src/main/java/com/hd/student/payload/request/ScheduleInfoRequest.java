package com.hd.student.payload.request;


import com.hd.student.entity.Weekdays;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleInfoRequest {
    private String weekdays;
    private Integer startAt;
    private Integer endAt;
    private Integer studyRoom;
}
