package com.hd.student.payload.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudyRoomRequest {
    @NotNull
    private String studyRoomName;
    @NotNull
    private Boolean isAvailable;
}
