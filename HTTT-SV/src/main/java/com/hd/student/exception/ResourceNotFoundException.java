package com.hd.student.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException{
    String resourceName;
    String FieldName;
    long FieldValue;
    String Value;
    public ResourceNotFoundException(String resourceName, String fieldName, long fieldValue) {
        super(String.format("Không tìm thấy %s, %s : %s ", resourceName,fieldName, fieldValue));
        this.resourceName = resourceName;
        FieldName = fieldName;
        FieldValue = fieldValue;
    }

    public ResourceNotFoundException(String resourceName, String fieldName, String value) {
        super(String.format("Không tìm thấy %s, %s : %s ", resourceName,fieldName, value));
        this.resourceName = resourceName;
        FieldName = fieldName;
        Value = value;
    }
}
