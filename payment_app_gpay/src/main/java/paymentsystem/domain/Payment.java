
package paymentsystem.domain;
public class Payment {
    public enum Status { INITIATED, PROCESSING, SUCCESS, FAILED }
    private final String paymentId;
    private final String fromAccount;
    private final String toAccount;
    private final long amount;
    private Status status=Status.INITIATED;
    public Payment(String paymentId,String from,String to,long amt){
        this.paymentId=paymentId; this.fromAccount=from; this.toAccount=to; this.amount=amt;
    }
    public String getPaymentId(){return paymentId;}
    public String getFromAccount(){return fromAccount;}
    public String getToAccount(){return toAccount;}
    public long getAmount(){return amount;}
    public Status getStatus(){return status;}
    public void setStatus(Status s){status=s;}
    public String toString(){return "Payment("+paymentId+","+fromAccount+"->"+toAccount+",amt="+amount+",st="+status+")";}
}
