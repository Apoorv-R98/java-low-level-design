
package paymentsystem.db;
import paymentsystem.domain.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;
import java.util.*;
public class InMemoryDB {
    public static final Map<String,User> users=new ConcurrentHashMap<>();
    public static final Map<String,Account> accounts=new ConcurrentHashMap<>();
    public static final Map<String,Payment> payments=new ConcurrentHashMap<>();
    public static final Map<String,PaymentResult> idempotencyStore=new ConcurrentHashMap<>();
    public static final Map<String,ReadWriteLock> accountLocks=new ConcurrentHashMap<>();
    public static ReadWriteLock getOrCreateLock(String id){
        return accountLocks.computeIfAbsent(id,k->new ReentrantReadWriteLock());
    }
}
