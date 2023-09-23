package com.hd.student.payload;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ApiResponse {
    private String message;
    private boolean success;
}
