package com.photosharing.exceptions;

public class InvalidInputException extends PhotoSharingException {
    public InvalidInputException(String message) {
        super("Invalid input: " + message);
    }
}

