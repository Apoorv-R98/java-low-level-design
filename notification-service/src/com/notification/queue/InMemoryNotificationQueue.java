package com.notification.queue;

import com.notification.models.Notification;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class InMemoryNotificationQueue implements NotificationQueue {

    private final BlockingQueue<Notification> queue =
            new LinkedBlockingQueue<>();

    @Override
    public void publish(Notification notification) {
        queue.offer(notification);
    }

    @Override
    public Notification consume() throws InterruptedException {
        return queue.take();
    }
}
