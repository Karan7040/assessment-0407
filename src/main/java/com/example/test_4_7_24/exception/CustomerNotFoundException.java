package com.example.test_4_7_24.exception;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException() {
        super("CustomerNotFoundException(404)");
    }
}
