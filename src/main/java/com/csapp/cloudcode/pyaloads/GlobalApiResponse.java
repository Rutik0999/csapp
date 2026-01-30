package com.csapp.cloudcode.pyaloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GlobalApiResponse<T> {
    private boolean success;
    private String message;
    private T data;
    private String httpStatus;
}