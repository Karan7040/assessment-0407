package com.colruyt.test3.service;

import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class CustomerNotFoundException extends NullPointerException{
}
