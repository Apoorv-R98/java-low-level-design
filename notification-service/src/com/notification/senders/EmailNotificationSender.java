package com.notification.senders;

import com.notification.models.Notification;
import com.notification.exceptions.NotificationDeliveryException;

public class EmailNotificationSender implements NotificationSender {

    @Override
    public void send(Notification notification) {
        if (notification.getRecipient() == null) {
            throw new NotificationDeliveryException("Invalid email recipient");
        }
        System.out.println("Sending EMAIL to " + notification.getRecipient());
    }
}
