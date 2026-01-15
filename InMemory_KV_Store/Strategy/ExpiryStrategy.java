public interface ExpiryStrategy<V> {
    boolean isExpired(ValueWrapper<V> wrapper);
}