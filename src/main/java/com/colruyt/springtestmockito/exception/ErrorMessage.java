package com.colruyt.springtestmockito.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Getter
@RequiredArgsConstructor
public enum ErrorMessage {
    CUSTOMER_NOT_FOUND("Customer Not Found");

    private final String message;

}
