package com.notification.observers;

import com.notification.models.NotificationEvent;

public interface NotificationObserver {
    void onEvent(NotificationEvent event);
}
