package com.example.e_commerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ResponseStatus
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiExceptionResponse> exceptionHandler(ApiException apiException){

        ApiExceptionResponse error = new ApiExceptionResponse(apiException.getMessage(), apiException.getStatus().value(), LocalDateTime.now());
        return new ResponseEntity<>(error,apiException.getStatus());

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiExceptionResponse> exceptionHandler(Exception exception){
        ApiExceptionResponse error = new ApiExceptionResponse(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(),LocalDateTime.now());
        return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
