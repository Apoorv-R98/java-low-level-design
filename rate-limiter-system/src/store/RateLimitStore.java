package store;

import model.TokenBucket;

import java.util.Deque;

public interface RateLimitStore {

    long incrementCounter(String key, long windowStart);

    long getCounter(String key, long windowStart);

    void addTimestamp(String key, long timestamp);

    Deque<Long> getTimestamps(String key);

    void saveTokenBucket(String key, TokenBucket bucket);

    TokenBucket getTokenBucket(String key);
}
