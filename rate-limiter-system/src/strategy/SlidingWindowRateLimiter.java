package strategy;

import exception.RateLimitExceededException;
import model.RateLimitPolicy;
import model.RateLimitResult;
import store.RateLimitStore;

import java.util.Deque;

public class SlidingWindowRateLimiter implements RateLimiterStrategy {

    private final RateLimitStore store;

    public SlidingWindowRateLimiter(RateLimitStore store) {
        this.store = store;
    }

    @Override
    public RateLimitResult allowRequest(String key,
                                        RateLimitPolicy policy) {

        long now = System.currentTimeMillis();

        Deque<Long> timestamps =
                store.getTimestamps(key);

        synchronized (timestamps) {

            while (!timestamps.isEmpty() &&
                    now - timestamps.peekFirst()
                            >= policy.getWindowSizeInMillis()) {

                timestamps.pollFirst();
            }

            if (timestamps.size()
                    >= policy.getMaxRequests()) {

                long retryAfter =
                        policy.getWindowSizeInMillis()
                                - (now - timestamps.peekFirst());

                throw new RateLimitExceededException(
                        "Sliding window limit exceeded",
                        retryAfter
                );
            }

            timestamps.addLast(now);
        }

        return new RateLimitResult(true, 0, "Allowed");
    }
}
