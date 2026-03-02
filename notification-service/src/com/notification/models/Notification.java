package com.notification.models;

public class Notification {
    private final String id;
    private final String recipient;
    private final String message;
    private final ChannelType channelType;

    public Notification(String id, String recipient, String message, ChannelType channelType) {
        this.id = id;
        this.recipient = recipient;
        this.message = message;
        this.channelType = channelType;
    }

    public String getId() { return id; }
    public String getRecipient() { return recipient; }
    public String getMessage() { return message; }
    public ChannelType getChannelType() { return channelType; }
}
