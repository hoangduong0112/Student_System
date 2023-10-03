package com.hd.student.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EnumNotFoundException extends RuntimeException{
    private String resourceName;
    private String fieldName;

    public EnumNotFoundException(String resourceName, String fieldName){
        super(String.format("Lỗi định dạng của %s, %s", resourceName,fieldName));
        this.resourceName = resourceName;
        fieldName = fieldName;
    }
}
