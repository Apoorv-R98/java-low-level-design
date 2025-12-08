
package paymentsystem.domain;
public class PaymentRequest {
    private final String fromAccount, toAccount, idempotencyKey;
    private final long amount;
    public PaymentRequest(String f,String t,long amt,String key){
        fromAccount=f; toAccount=t; amount=amt; idempotencyKey=key;
    }
    public String getFromAccount(){return fromAccount;}
    public String getToAccount(){return toAccount;}
    public long getAmount(){return amount;}
    public String getIdempotencyKey(){return idempotencyKey;}
}
