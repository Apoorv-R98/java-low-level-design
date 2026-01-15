public class Main {
    public static void main(String[] args) throws InterruptedException {

        KeyValueStore<String, String> store =
                new InMemoryKeyValueStore<>(new TTLExpiryStrategy<>());

        store.put("user1", "Apoorv");
        System.out.println(store.get("user1"));

        store.put("user1", "Rastogi");
        System.out.println(store.get("user1"));

        store.put("session", "xyz123", 2000);
        System.out.println(store.get("session"));

        Thread.sleep(2500);

        try {
            store.get("session");
        } catch (KeyExpiredException ex) {
            System.out.println(ex.getMessage());
        }

        try {
            store.put(null, "invalid");
        } catch (InvalidKeyException ex) {
            System.out.println(ex.getMessage());
        }

        try {
            store.put("badTTL", "oops", -5);
        } catch (InvalidTTLException ex) {
            System.out.println(ex.getMessage());
        }

        store.put("temp", "data");
        store.delete("temp");
        System.out.println(store.get("temp"));
    }
}