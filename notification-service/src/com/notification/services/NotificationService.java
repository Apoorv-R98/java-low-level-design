package com.notification.services;

import com.notification.models.Notification;
import com.notification.models.NotificationEvent;
import com.notification.observers.NotificationObserver;
import com.notification.exceptions.NotificationException;

import java.util.List;

public class NotificationService {

    private final NotificationSenderFactory factory;
    private final List<NotificationObserver> observers;

    public NotificationService(NotificationSenderFactory factory,
                               List<NotificationObserver> observers) {
        this.factory = factory;
        this.observers = observers;
    }

    public void process(Notification notification) {
        try {
            var sender = factory.getSender(notification.getChannelType());
            sender.send(notification);

            notifyObservers(
                    new NotificationEvent(notification, true, "Delivered")
            );

        } catch (NotificationException ex) {

            notifyObservers(
                    new NotificationEvent(notification, false, ex.getMessage())
            );

            throw ex;
        }
    }

    private void notifyObservers(NotificationEvent event) {
        for (NotificationObserver observer : observers) {
            observer.onEvent(event);
        }
    }
}
