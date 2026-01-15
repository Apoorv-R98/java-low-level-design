public class ValueWrapper<V> {
    private final V value;
    private final long expiryTime;

    public ValueWrapper(V value, long ttlMillis) {
        this.value = value;
        this.expiryTime = ttlMillis > 0 ? System.currentTimeMillis() + ttlMillis : -1;
    }

    public V getValue() {
        return value;
    }

    public boolean isExpired() {
        return expiryTime != -1 && System.currentTimeMillis() > expiryTime;
    }
}