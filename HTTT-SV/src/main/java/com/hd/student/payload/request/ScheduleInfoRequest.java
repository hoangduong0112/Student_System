package com.hd.student.payload.request;


import com.hd.student.validation.ValidateScheduleOrder;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ValidateScheduleOrder
public class ScheduleInfoRequest {
    @NotNull
    private String weekdays;
    //Xu ly startAt > endAt
    @NotNull
    private Integer startAt;
    @NotNull
    private Integer endAt;
    @NotNull
    private Integer studyRoom;
}
