public class TTLExpiryStrategy<V> implements ExpiryStrategy<V> {
    @Override
    public boolean isExpired(ValueWrapper<V> wrapper) {
        return wrapper.isExpired();
    }
}