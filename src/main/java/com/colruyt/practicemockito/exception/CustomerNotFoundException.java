package com.colruyt.practicemockito.exception;




public class CustomerNotFoundException extends RuntimeException{
        public CustomerNotFoundException(String message) {
            super(message);
        }
}
