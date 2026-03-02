package com.notification.exceptions;

import com.notification.models.ChannelType;

public class UnsupportedChannelException extends NotificationException {
    public UnsupportedChannelException(ChannelType type) {
        super("Unsupported channel: " + type);
    }
}
