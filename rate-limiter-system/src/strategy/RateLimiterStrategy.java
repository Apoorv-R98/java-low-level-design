package strategy;

import model.RateLimitPolicy;
import model.RateLimitResult;

public interface RateLimiterStrategy {

    RateLimitResult allowRequest(String key,
                                 RateLimitPolicy policy);
}
