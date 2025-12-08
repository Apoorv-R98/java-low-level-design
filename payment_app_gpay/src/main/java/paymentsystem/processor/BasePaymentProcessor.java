
package paymentsystem.processor;
import paymentsystem.domain.*;
import paymentsystem.idempotency.IdempotencyService;
import paymentsystem.validation.*;
import java.util.*;
public abstract class BasePaymentProcessor {
    protected final IdempotencyService idem=new IdempotencyService();
    public PaymentResult process(PaymentRequest r){
        Optional<PaymentResult> prev=idem.check(r.getIdempotencyKey());
        if(prev.isPresent()) return prev.get();
        ValidationChain vc=new ValidationChain().add(new AmountValidator()).add(new BalanceValidator());
        try{ vc.execute(r); } catch(Exception e){
            PaymentResult res=new PaymentResult(UUID.randomUUID().toString(),Payment.Status.FAILED,e.getMessage());
            idem.save(r.getIdempotencyKey(),res); return res;
        }
        Payment p=new Payment(UUID.randomUUID().toString(),r.getFromAccount(),r.getToAccount(),r.getAmount());
        p.setStatus(Payment.Status.PROCESSING);
        try{
            executeTransfer(p);
            p.setStatus(Payment.Status.SUCCESS);
            PaymentResult res=new PaymentResult(p.getPaymentId(),Payment.Status.SUCCESS,null);
            idem.save(r.getIdempotencyKey(),res); return res;
        } catch(Exception e){
            p.setStatus(Payment.Status.FAILED);
            PaymentResult res=new PaymentResult(p.getPaymentId(),Payment.Status.FAILED,e.getMessage());
            idem.save(r.getIdempotencyKey(),res); return res;
        }
    }
    protected abstract void executeTransfer(Payment p)throws Exception;
}
