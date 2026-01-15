import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class InMemoryKeyValueStore<K, V> implements KeyValueStore<K, V> {

    private final ConcurrentMap<K, ValueWrapper<V>> store;
    private final ExpiryStrategy<V> expiryStrategy;

    public InMemoryKeyValueStore(ExpiryStrategy<V> expiryStrategy) {
        if (expiryStrategy == null) {
            throw new IllegalArgumentException("ExpiryStrategy cannot be null");
        }
        this.store = new ConcurrentHashMap<>();
        this.expiryStrategy = expiryStrategy;
    }

    @Override
    public void put(K key, V value) {
        put(key, value, -1);
    }

    @Override
    public void put(K key, V value, long ttlMillis) {
        validateKey(key);
        validateTTL(ttlMillis);
        store.put(key, new ValueWrapper<>(value, ttlMillis));
    }

    @Override
    public V get(K key) {
        validateKey(key);
        ValueWrapper<V> wrapper = store.get(key);
        if (wrapper == null) {
            return null;
        }
        if (expiryStrategy.isExpired(wrapper)) {
            store.remove(key);
            throw new KeyExpiredException("Key has expired: " + key);
        }
        return wrapper.getValue();
    }

    @Override
    public void delete(K key) {
        validateKey(key);
        store.remove(key);
    }

    @Override
    public boolean containsKey(K key) {
        validateKey(key);
        return store.containsKey(key);
    }

    private void validateKey(K key) {
        if (key == null) {
            throw new InvalidKeyException("Key cannot be null");
        }
    }

    private void validateTTL(long ttlMillis) {
        if (ttlMillis < -1) {
            throw new InvalidTTLException("TTL must be -1 or a positive value");
        }
    }
}