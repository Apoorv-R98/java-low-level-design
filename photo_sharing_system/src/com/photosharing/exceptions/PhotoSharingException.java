package com.photosharing.exceptions;

public class PhotoSharingException extends RuntimeException {
    public PhotoSharingException(String message) {
        super(message);
    }

    public PhotoSharingException(String message, Throwable cause) {
        super(message, cause);
    }
}
