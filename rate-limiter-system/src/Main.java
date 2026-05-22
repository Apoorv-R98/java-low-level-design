import exception.RateLimitExceededException;
import model.RateLimitConfig;
import model.RateLimitResult;
import model.RateLimiterType;
import model.Request;
import service.PolicyManager;
import service.RateLimiterService;
import store.InMemoryConfigStore;
import store.InMemoryRateLimitStore;
import store.RateLimitStore;

public class Main {

    public static void main(String[] args) {

        InMemoryConfigStore configStore =
                new InMemoryConfigStore();

        configStore.put(
                "DEFAULT",
                new RateLimitConfig(
                        RateLimiterType.SLIDING_WINDOW,
                        5,
                        10000
                )
        );

        PolicyManager policyManager =
                new PolicyManager(configStore);

        RateLimitStore store =
                new InMemoryRateLimitStore();

        RateLimiterService service =
                new RateLimiterService(
                        policyManager,
                        store
                );

        for (int i = 1; i <= 10; i++) {

            try {

                Request request =
                        new Request("user-1", "api-key", "/payments");

                RateLimitResult result =
                        service.allowRequest(request);

                System.out.println(
                        "Request " + i +
                                " Allowed: " +
                                result.isAllowed()
                );

            } catch (RateLimitExceededException ex) {

                System.out.println(
                        "Blocked: " +
                                ex.getMessage() +
                                ", Retry After: " +
                                ex.getRetryAfterMillis()
                );
            }
        }
    }
}
