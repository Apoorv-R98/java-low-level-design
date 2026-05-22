package exception;

public class RateLimitExceededException
        extends RuntimeException {

    private final long retryAfterMillis;

    public RateLimitExceededException(
            String message,
            long retryAfterMillis
    ) {
        super(message);
        this.retryAfterMillis = retryAfterMillis;
    }

    public long getRetryAfterMillis() {
        return retryAfterMillis;
    }
}
