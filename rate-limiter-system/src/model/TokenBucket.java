package model;

public class TokenBucket {

    private final int capacity;
    private final double refillRatePerSecond;

    private double tokens;
    private long lastRefillTimestamp;

    public TokenBucket(int capacity,
                       double refillRatePerSecond) {

        this.capacity = capacity;
        this.refillRatePerSecond = refillRatePerSecond;
        this.tokens = capacity;
        this.lastRefillTimestamp =
                System.currentTimeMillis();
    }

    public synchronized boolean tryConsume() {

        refill();

        if (tokens >= 1) {
            tokens -= 1;
            return true;
        }

        return false;
    }

    private void refill() {

        long now = System.currentTimeMillis();

        double refillTokens =
                ((now - lastRefillTimestamp) / 1000.0)
                        * refillRatePerSecond;

        tokens = Math.min(
                capacity,
                tokens + refillTokens
        );

        lastRefillTimestamp = now;
    }
}
