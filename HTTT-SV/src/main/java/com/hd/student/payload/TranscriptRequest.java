package com.hd.student.payload;

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
    private Integer fromSemeter;
    private Integer toSemeter;

    private Integer quantity;

    private String contactPhone;

    private Boolean isSealed;
}
