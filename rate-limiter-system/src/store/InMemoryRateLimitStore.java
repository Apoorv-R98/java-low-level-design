package store;

import model.TokenBucket;

import java.util.Deque;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

public class InMemoryRateLimitStore implements RateLimitStore {

    private final Map<String, Long> counters = new ConcurrentHashMap<>();
    private final Map<String, Deque<Long>> slidingLogs = new ConcurrentHashMap<>();
    private final Map<String, TokenBucket> tokenBuckets = new ConcurrentHashMap<>();

    @Override
    public long incrementCounter(String key, long windowStart) {
        return counters.merge(key + ":" + windowStart, 1L, Long::sum);
    }

    @Override
    public long getCounter(String key, long windowStart) {
        return counters.getOrDefault(key + ":" + windowStart, 0L);
    }

    @Override
    public void addTimestamp(String key, long timestamp) {
        slidingLogs
                .computeIfAbsent(key, k -> new ConcurrentLinkedDeque<>())
                .addLast(timestamp);
    }

    @Override
    public Deque<Long> getTimestamps(String key) {
        return slidingLogs.computeIfAbsent(key,
                k -> new ConcurrentLinkedDeque<>());
    }

    @Override
    public void saveTokenBucket(String key, TokenBucket bucket) {
        tokenBuckets.put(key, bucket);
    }

    @Override
    public TokenBucket getTokenBucket(String key) {
        return tokenBuckets.get(key);
    }
}
