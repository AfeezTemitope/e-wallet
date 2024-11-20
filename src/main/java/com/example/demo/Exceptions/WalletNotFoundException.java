package com.example.demo.Exceptions;

public class WalletNotFoundException extends Exception{
    public WalletNotFoundException(String message, Long id) {
        super(message);
    }
}
