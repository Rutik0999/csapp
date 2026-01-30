package com.csapp.cloudcode.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException{
    private String fieldName;
    private String fieldValue;
//    private String message;

    public ResourceNotFoundException(String fieldName, String fieldValue,String message){
        super("resource "+fieldName+" with value"+fieldValue+" "+message);
        this.fieldName=fieldName;
        this.fieldValue=fieldValue;
//      this.message=message;
    }

}