package com.kodilla.kodilla.diplomaBackend.service.exceptions;

public class EmailExistsException extends Exception {

    public EmailExistsException(String message) {
        super(message);
    }
}
