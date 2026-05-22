package store;

import model.RateLimitConfig;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryConfigStore
        implements ConfigStore {

    private final Map<String, RateLimitConfig> configs =
            new ConcurrentHashMap<>();

    public void put(String key,
                    RateLimitConfig config) {
        configs.put(key, config);
    }

    @Override
    public RateLimitConfig getConfig(String key) {
        return configs.get(key);
    }
}
