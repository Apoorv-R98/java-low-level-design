package com.notification.observers;

import com.notification.models.NotificationEvent;

public class LoggingObserver implements NotificationObserver {

    @Override
    public void onEvent(NotificationEvent event) {
        System.out.println("LOG: Notification " +
                event.getNotification().getId() +
                " success=" + event.isSuccess());
    }
}
