package strategy;

import exception.RateLimitExceededException;
import model.RateLimitPolicy;
import model.RateLimitResult;
import model.TokenBucket;
import store.RateLimitStore;

public class TokenBucketRateLimiter
        implements RateLimiterStrategy {

    private final RateLimitStore store;

    public TokenBucketRateLimiter(RateLimitStore store) {
        this.store = store;
    }

    @Override
    public RateLimitResult allowRequest(String key,
                                        RateLimitPolicy policy) {

        TokenBucket bucket =
                store.getTokenBucket(key);

        if (bucket == null) {

            bucket = new TokenBucket(
                    policy.getMaxRequests(),
                    policy.getMaxRequests()
                            / (policy.getWindowSizeInMillis() / 1000.0)
            );

            store.saveTokenBucket(key, bucket);
        }

        boolean allowed = bucket.tryConsume();

        if (!allowed) {
            throw new RateLimitExceededException(
                    "Token bucket exhausted",
                    1000
            );
        }

        return new RateLimitResult(true, 0, "Allowed");
    }
}
