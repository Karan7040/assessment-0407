package org.example.springtest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ProblemDetail handler(CustomerNotFoundException customerNotFoundException)
    {
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, customerNotFoundException.getMessage());
    }
}
