package model;

import java.time.Instant;

public class Request {

    private final String userId;
    private final String apiKey;
    private final String ipAddress;
    private final String endpoint;
    private final Instant timestamp;

    public Request(String userId, String apiKey, String ipAddress,
                   String endpoint, Instant timestamp) {
        this.userId = userId;
        this.apiKey = apiKey;
        this.ipAddress = ipAddress;
        this.endpoint = endpoint;
        this.timestamp = timestamp != null ? timestamp : Instant.now();
    }

    public Request(String userId, String apiKey, String endpoint) {
        this(userId, apiKey, null, endpoint, Instant.now());
    }

    public String getUserId() {
        return userId;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public Instant getTimestamp() {
        return timestamp;
    }
}
