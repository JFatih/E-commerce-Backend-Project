package com.example.e_commerce.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
public class StoreDataExistValidationException extends RuntimeException{

    private HttpStatus status;

    private List<String> errors;

    public StoreDataExistValidationException(HttpStatus status){
        this.status = status;
        this.errors = new ArrayList<>();
    }

    public StoreDataExistValidationException(String message, HttpStatus status){
        super(message);
        this.status = status;
        this.errors = new ArrayList<>();
    }

    public  StoreDataExistValidationException(List<String> errors, HttpStatus status){
        super(String.join(" ,", errors));
        this.status = status;
        this.errors = errors;
    }

    public void addError(String e){
        this.errors.add(e);
    }
}
