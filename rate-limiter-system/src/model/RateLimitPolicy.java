package model;

public class RateLimitPolicy {

    private final int maxRequests;
    private final long windowSizeInMillis;

    public RateLimitPolicy(int maxRequests, long windowSizeInMillis) {
        this.maxRequests = maxRequests;
        this.windowSizeInMillis = windowSizeInMillis;
    }

    public int getMaxRequests() {
        return maxRequests;
    }

    public long getWindowSizeInMillis() {
        return windowSizeInMillis;
    }
}
