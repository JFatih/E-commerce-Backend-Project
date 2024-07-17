package com.example.e_commerce.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public class ApiException extends RuntimeException{

    private HttpStatus status;

    public ApiException(String message, HttpStatus status){
        super(message);
        this.status = status;
    }
}
