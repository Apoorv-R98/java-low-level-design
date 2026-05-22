package service;

import exception.ConfigurationNotFoundException;
import model.RateLimitConfig;
import model.Request;
import store.ConfigStore;

public class PolicyManager {

    private final ConfigStore configStore;

    public PolicyManager(ConfigStore configStore) {
        this.configStore = configStore;
    }

    public RateLimitConfig resolve(Request request) {

        String userKey =
                "USER:" + request.getUserId();

        RateLimitConfig config =
                configStore.getConfig(userKey);

        if (config != null) {
            return config;
        }

        String apiKey =
                "API:" + request.getApiKey();

        config = configStore.getConfig(apiKey);

        if (config != null) {
            return config;
        }

        String endpointKey =
                "ENDPOINT:" + request.getEndpoint();

        config = configStore.getConfig(endpointKey);

        if (config != null) {
            return config;
        }

        config = configStore.getConfig("DEFAULT");

        if (config == null) {
            throw new ConfigurationNotFoundException(
                    "No configuration found"
            );
        }

        return config;
    }
}
