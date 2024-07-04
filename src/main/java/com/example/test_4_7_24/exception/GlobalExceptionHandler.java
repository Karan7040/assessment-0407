package com.example.test_4_7_24.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ProblemDetail> handleRunTimeException(){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,"CustomerNotFoundException(404)");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problemDetail);

    }
}
