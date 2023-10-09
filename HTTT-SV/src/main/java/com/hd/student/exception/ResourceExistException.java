package com.hd.student.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceExistException extends RuntimeException{
    private String message;

    public ResourceExistException(String message) {
        super(String.format("%s", message));
        this.message = message;
    }
}
