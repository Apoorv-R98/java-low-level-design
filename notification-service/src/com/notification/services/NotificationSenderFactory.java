package com.notification.services;

import com.notification.models.ChannelType;
import com.notification.senders.NotificationSender;
import com.notification.exceptions.UnsupportedChannelException;

import java.util.Map;

public class NotificationSenderFactory {

    private final Map<ChannelType, NotificationSender> senderMap;

    public NotificationSenderFactory(Map<ChannelType, NotificationSender> senderMap) {
        this.senderMap = senderMap;
    }

    public NotificationSender getSender(ChannelType type) {
        NotificationSender sender = senderMap.get(type);
        if (sender == null) {
            throw new UnsupportedChannelException(type);
        }
        return sender;
    }
}
