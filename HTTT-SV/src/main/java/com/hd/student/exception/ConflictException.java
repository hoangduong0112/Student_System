package com.hd.student.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConflictException extends RuntimeException{
    private String message;

    public ConflictException(String message) {
        super(String.format("%s", message));
        this.message = message;
    }
}
