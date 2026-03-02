package com.notification.queue;

import com.notification.models.Notification;

public interface NotificationQueue {
    void publish(Notification notification);
    Notification consume() throws InterruptedException;
}
