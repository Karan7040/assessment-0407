package com.colruyt.test3.service;

import org.springframework.web.bind.annotation.ControllerAdvice;


// todo :extending what null pointer?
@ControllerAdvice
public class CustomerNotFoundException extends NullPointerException{
}
