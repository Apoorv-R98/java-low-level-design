package factory;

import model.RateLimiterType;
import store.RateLimitStore;
import strategy.FixedWindowRateLimiter;
import strategy.RateLimiterStrategy;
import strategy.SlidingWindowRateLimiter;
import strategy.TokenBucketRateLimiter;

public class RateLimiterFactory {

    public static RateLimiterStrategy create(
            RateLimiterType type,
            RateLimitStore store
    ) {

        return switch (type) {

            case FIXED_WINDOW ->
                    new FixedWindowRateLimiter(store);

            case SLIDING_WINDOW ->
                    new SlidingWindowRateLimiter(store);

            case TOKEN_BUCKET ->
                    new TokenBucketRateLimiter(store);
        };
    }
}
