package com.kodilla.kodilla.diplomaBackend.service;

public class WrongCredentialsException extends Exception {

    public WrongCredentialsException(String message) {
        super(message);
    }
}
