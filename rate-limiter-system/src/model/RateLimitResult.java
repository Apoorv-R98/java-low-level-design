package model;

public class RateLimitResult {

    private final boolean allowed;
    private final long retryAfterMillis;
    private final String reason;

    public RateLimitResult(boolean allowed,
                           long retryAfterMillis,
                           String reason) {
        this.allowed = allowed;
        this.retryAfterMillis = retryAfterMillis;
        this.reason = reason;
    }

    public boolean isAllowed() {
        return allowed;
    }

    public long getRetryAfterMillis() {
        return retryAfterMillis;
    }

    public String getReason() {
        return reason;
    }
}
