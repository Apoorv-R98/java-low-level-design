
package paymentsystem.strategies;
import paymentsystem.domain.*;
import paymentsystem.db.InMemoryDB;
import paymentsystem.exceptions.TransferFailedException;
import java.util.concurrent.locks.*;
public class ExternalUPITransferStrategy implements TransferStrategy {
    public void executeTransfer(Payment p)throws Exception{
        String a=p.getFromAccount(), b=p.getToAccount();
        String first=a.compareTo(b)<=0?a:b, second=a.compareTo(b)<=0?b:a;
        ReadWriteLock l1=InMemoryDB.getOrCreateLock(first), l2=InMemoryDB.getOrCreateLock(second);
        Lock w1=l1.writeLock(), w2=l2.writeLock();
        w1.lock(); w2.lock();
        try{
            Account from=InMemoryDB.accounts.get(p.getFromAccount());
            Account to=InMemoryDB.accounts.get(p.getToAccount());
            if(from.getBalance()<p.getAmount()) throw new TransferFailedException("Insufficient balance");
            // simulate external call
            Thread.sleep(20);
            from.setBalance(from.getBalance()-p.getAmount());
            to.setBalance(to.getBalance()+p.getAmount());
        } finally { w2.unlock(); w1.unlock(); }
    }
}
