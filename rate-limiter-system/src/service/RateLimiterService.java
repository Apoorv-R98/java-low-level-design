package service;

import factory.RateLimiterFactory;
import model.RateLimitConfig;
import model.RateLimitPolicy;
import model.RateLimitResult;
import model.Request;
import store.RateLimitStore;
import strategy.RateLimiterStrategy;

public class RateLimiterService {

    private final PolicyManager policyManager;
    private final RateLimitStore store;

    public RateLimiterService(
            PolicyManager policyManager,
            RateLimitStore store
    ) {
        this.policyManager = policyManager;
        this.store = store;
    }

    public RateLimitResult allowRequest(
            Request request
    ) {

        RateLimitConfig config =
                policyManager.resolve(request);

        RateLimiterStrategy strategy =
                RateLimiterFactory.create(
                        config.getType(),
                        store
                );

        RateLimitPolicy policy =
                new RateLimitPolicy(
                        config.getMaxRequests(),
                        config.getWindowSizeMillis()
                );

        String key =
                request.getUserId() + ":" +
                        request.getEndpoint();

        return strategy.allowRequest(key, policy);
    }
}
