package com.rating.exceptions;

public class RatingSystemException extends RuntimeException {
    public RatingSystemException(String message) {
        super(message);
    }

    public RatingSystemException(String message, Throwable cause) {
        super(message, cause);
    }
}

