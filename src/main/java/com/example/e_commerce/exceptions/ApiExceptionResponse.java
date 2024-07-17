package com.example.e_commerce.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class ApiExceptionResponse {

    private String message;

    private int status;

    private LocalDateTime time;
}
