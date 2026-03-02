package com.notification;

import com.notification.models.Notification;
import com.notification.models.ChannelType;
import com.notification.observers.LoggingObserver;
import com.notification.observers.NotificationObserver;
import com.notification.queue.InMemoryNotificationQueue;
import com.notification.queue.NotificationQueue;
import com.notification.senders.EmailNotificationSender;
import com.notification.services.NotificationSenderFactory;
import com.notification.services.NotificationService;
import com.notification.exceptions.NotificationException;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {

        NotificationSenderFactory factory =
                new NotificationSenderFactory(
                        Map.of(
                                ChannelType.EMAIL, new EmailNotificationSender()
                        )
                );

        List<NotificationObserver> observers =
                List.of(new LoggingObserver());

        NotificationService service =
                new NotificationService(factory, observers);

        NotificationQueue queue =
                new InMemoryNotificationQueue();

        ExecutorService executor = Executors.newSingleThreadExecutor();

        executor.submit(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Notification notification = queue.consume();
                    service.process(notification);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } catch (NotificationException ex) {
                    System.out.println("Error processing notification: " + ex.getMessage());
                }
            }
        });

        queue.publish(new Notification(
                "1",
                "user@example.com",
                "Welcome!",
                ChannelType.EMAIL
        ));
    }
}
