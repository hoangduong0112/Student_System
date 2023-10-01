package com.hd.student.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DataIntegrityViolationException extends RuntimeException {
    private String message;

    public DataIntegrityViolationException(String message){
        super(message);
        message = this.message;
    }
}
