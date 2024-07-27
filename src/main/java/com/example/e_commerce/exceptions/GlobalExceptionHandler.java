package com.example.e_commerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiExceptionResponse> exceptionHandler(ApiException apiException){

        ApiExceptionResponse error = new ApiExceptionResponse(apiException.getMessage(), apiException.getStatus().value(), LocalDateTime.now());
        return new ResponseEntity<>(error,apiException.getStatus());

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDetailsWithValidation> exceptionHandler(MethodArgumentNotValidException err, WebRequest webRequest){

        Map<String, String> errors = new HashMap<>();
        List<ObjectError> errorList = err.getBindingResult().getAllErrors();

        errorList.forEach( (error) -> {

            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);

        });

        ErrorDetailsWithValidation validErr = new ErrorDetailsWithValidation(
                LocalDateTime.now(), webRequest.getDescription(false),
                HttpStatus.BAD_REQUEST.value(), errors);

        return new ResponseEntity<>(validErr, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiExceptionResponse> exceptionHandler(Exception exception){
        ApiExceptionResponse error = new ApiExceptionResponse(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(),LocalDateTime.now());
        return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(StoreDataExistValidationException.class)
    public ResponseEntity<ApiExceptionResponse> exceptionHandler(StoreDataExistValidationException e){
        ApiExceptionResponse error = new ApiExceptionResponse(e.getErrors().toString(),e.getStatus().value(),LocalDateTime.now());
        return new ResponseEntity<>(error,e.getStatus());
    }
}
