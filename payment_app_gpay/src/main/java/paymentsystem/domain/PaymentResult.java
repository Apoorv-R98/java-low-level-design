
package paymentsystem.domain;
public class PaymentResult {
    private final String paymentId;
    private final Payment.Status status;
    private final String reason;
    public PaymentResult(String id, Payment.Status st, String reason){
        this.paymentId=id; this.status=st; this.reason=reason;
    }
    public String getPaymentId(){return paymentId;}
    public Payment.Status getStatus(){return status;}
    public String getFailureReason(){return reason;}
    public String toString(){ return "PaymentResult(id="+paymentId+",status="+status+(reason!=null?(",reason="+reason):"")+")"; }
}
