package store;

import model.RateLimitConfig;

public interface ConfigStore {

    RateLimitConfig getConfig(String key);
}
