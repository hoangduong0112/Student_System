package com.hd.student.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AccessDeniedException extends RuntimeException{
    private String message;

    public AccessDeniedException(String message) {
        super(message);
        this.message = message;
    }
}
