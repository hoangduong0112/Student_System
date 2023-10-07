package com.hd.student.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ForeignKeyViolationException extends RuntimeException{
    private String message;

    public ForeignKeyViolationException(String message){
        super(message);
        message = this.message;
    }
}
