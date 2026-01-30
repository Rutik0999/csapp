package com.csapp.cloudcode.exception;

import com.csapp.cloudcode.pyaloads.GlobalApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<GlobalApiResponse> resourceNotFoundHandler(ResourceNotFoundException ex){
        GlobalApiResponse globalApiResponse = new GlobalApiResponse(
                false,
                ex.getMessage(),
                null,
                HttpStatus.BAD_REQUEST.toString()
        );
        return new ResponseEntity<>(globalApiResponse, HttpStatus.BAD_REQUEST);
    }
}