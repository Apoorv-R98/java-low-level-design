package model;

public class RateLimitConfig {

    private final RateLimiterType type;
    private final int maxRequests;
    private final long windowSizeMillis;

    public RateLimitConfig(RateLimiterType type,
                           int maxRequests,
                           long windowSizeMillis) {
        this.type = type;
        this.maxRequests = maxRequests;
        this.windowSizeMillis = windowSizeMillis;
    }

    public RateLimiterType getType() {
        return type;
    }

    public int getMaxRequests() {
        return maxRequests;
    }

    public long getWindowSizeMillis() {
        return windowSizeMillis;
    }
}
