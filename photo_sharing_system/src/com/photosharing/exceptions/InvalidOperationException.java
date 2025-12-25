package com.photosharing.exceptions;

public class InvalidOperationException extends PhotoSharingException {
    public InvalidOperationException(String message) {
        super("Invalid operation: " + message);
    }
}

