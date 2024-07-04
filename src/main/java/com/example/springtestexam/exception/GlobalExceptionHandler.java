package com.example.springtestexam.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    // todo : reformat code.. before pushing enable auto-reformat
    @ExceptionHandler
    public ResponseEntity<ProblemDetail> handleCustomerNotFoundException(CustomerNotFoundExcetpion customerNotFoundExcetpion)
    {
        ProblemDetail problemDetail=ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, customerNotFoundExcetpion.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problemDetail);
    }
}
