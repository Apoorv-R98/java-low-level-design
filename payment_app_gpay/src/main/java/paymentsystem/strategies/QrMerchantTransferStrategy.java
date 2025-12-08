
package paymentsystem.strategies;
import paymentsystem.domain.*;
import paymentsystem.db.InMemoryDB;
import java.util.concurrent.locks.*;
public class QrMerchantTransferStrategy implements TransferStrategy {
    public void executeTransfer(Payment p)throws Exception{
        // QR merchant may include a fee, simplified here
        long fee = Math.max(0, p.getAmount()/100); // 1% fee
        String a=p.getFromAccount(), b=p.getToAccount();
        String first=a.compareTo(b)<=0?a:b, second=a.compareTo(b)<=0?b:a;
        ReadWriteLock l1=InMemoryDB.getOrCreateLock(first), l2=InMemoryDB.getOrCreateLock(second);
        Lock w1=l1.writeLock(), w2=l2.writeLock();
        w1.lock(); w2.lock();
        try{
            Account from=InMemoryDB.accounts.get(p.getFromAccount());
            Account to=InMemoryDB.accounts.get(p.getToAccount());
            if(from.getBalance()<p.getAmount()+fee) throw new RuntimeException("Insufficient balance incl. fee");
            Thread.sleep(10);
            from.setBalance(from.getBalance()-p.getAmount()-fee);
            to.setBalance(to.getBalance()+p.getAmount()); // merchant receives amount
            // fee could go to platform account; omitted
        } finally { w2.unlock(); w1.unlock(); }
    }
}
