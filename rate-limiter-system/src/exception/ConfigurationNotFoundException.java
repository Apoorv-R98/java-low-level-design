package exception;

public class ConfigurationNotFoundException
        extends RuntimeException {

    public ConfigurationNotFoundException(
            String message
    ) {
        super(message);
    }
}
