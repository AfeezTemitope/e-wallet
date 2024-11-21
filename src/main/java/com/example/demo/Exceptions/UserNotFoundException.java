package com.example.demo.Exceptions;

public class UserNotFoundException extends Exception{
    public UserNotFoundException(String customerNotFound) {
        super(customerNotFound);

    }
}
