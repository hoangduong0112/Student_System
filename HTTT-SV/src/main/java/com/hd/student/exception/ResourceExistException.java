package com.hd.student.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceExistException extends RuntimeException{
    private String fieldName;
    private String fieldValue;

    public ResourceExistException(String fieldName, String fieldValue){
        super(String.format("Dữ liệu đã tồn tại ở %s : %s ", fieldName, fieldValue));
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
