package com.notification.models;

public class NotificationEvent {
    private final Notification notification;
    private final boolean success;
    private final String message;

    public NotificationEvent(Notification notification, boolean success, String message) {
        this.notification = notification;
        this.success = success;
        this.message = message;
    }

    public Notification getNotification() { return notification; }
    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }
}
