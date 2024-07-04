package com.example.fetchcustomerdetialsexam.exception;

public class CustomerNotFoundException  extends RuntimeException{

  public CustomerNotFoundException() {
        super("ID _Not_Found");
  }

}

