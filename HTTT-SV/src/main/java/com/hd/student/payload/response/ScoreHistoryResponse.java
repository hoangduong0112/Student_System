package com.hd.student.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScoreHistoryResponse {
    private int id; //id semesteruser
    private String semesterName;
    private Double score;

}
