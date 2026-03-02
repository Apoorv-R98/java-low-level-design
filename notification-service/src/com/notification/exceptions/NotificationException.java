package com.notification.exceptions;

public abstract class NotificationException extends RuntimeException {
    public NotificationException(String message) {
        super(message);
    }
}
