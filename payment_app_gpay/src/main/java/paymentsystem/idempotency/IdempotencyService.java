
package paymentsystem.idempotency;
import paymentsystem.db.InMemoryDB;
import paymentsystem.domain.PaymentResult;
import java.util.*;
public class IdempotencyService {
    public Optional<PaymentResult> check(String key){
        return Optional.ofNullable(InMemoryDB.idempotencyStore.get(key));
    }
    public void save(String key, PaymentResult r){
        InMemoryDB.idempotencyStore.put(key,r);
    }
}
