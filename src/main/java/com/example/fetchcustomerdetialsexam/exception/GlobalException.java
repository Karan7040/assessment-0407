package com.example.fetchcustomerdetialsexam.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalException {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ProblemDetail> handleRunTimeException() {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,"id not found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problemDetail);
    }
}
