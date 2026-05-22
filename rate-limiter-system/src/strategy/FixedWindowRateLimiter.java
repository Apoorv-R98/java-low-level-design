package strategy;

import exception.RateLimitExceededException;
import model.RateLimitPolicy;
import model.RateLimitResult;
import store.RateLimitStore;

public class FixedWindowRateLimiter implements RateLimiterStrategy {

    private final RateLimitStore store;

    public FixedWindowRateLimiter(RateLimitStore store) {
        this.store = store;
    }

    @Override
    public RateLimitResult allowRequest(String key,
                                        RateLimitPolicy policy) {

        long currentTime = System.currentTimeMillis();
        long windowStart =
                currentTime / policy.getWindowSizeInMillis();

        long count =
                store.incrementCounter(key, windowStart);

        if (count > policy.getMaxRequests()) {
            throw new RateLimitExceededException(
                    "Fixed window limit exceeded",
                    policy.getWindowSizeInMillis()
            );
        }

        return new RateLimitResult(true, 0, "Allowed");
    }
}
