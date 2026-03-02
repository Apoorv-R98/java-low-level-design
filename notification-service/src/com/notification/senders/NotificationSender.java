package com.notification.senders;

import com.notification.models.Notification;

public interface NotificationSender {
    void send(Notification notification);
}
