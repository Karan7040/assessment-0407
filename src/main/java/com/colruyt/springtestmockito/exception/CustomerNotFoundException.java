package com.colruyt.springtestmockito.exception;




public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException() {
        super(ErrorMessage.CUSTOMER_NOT_FOUND.getMessage());
    }
    private String message1;

    public CustomerNotFoundException(String message1) {
        this.message1 = message1;
    }
}
