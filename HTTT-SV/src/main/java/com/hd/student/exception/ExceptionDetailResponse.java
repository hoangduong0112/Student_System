package com.hd.student.exception;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionDetailResponse {
    private int statusCode;
    private Date timestamp;
    private String message;
    private String details;
}
