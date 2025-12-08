
package paymentsystem.processor;
import paymentsystem.domain.Payment;
import paymentsystem.strategies.TransferStrategy;
public class P2PPaymentProcessor extends BasePaymentProcessor {
    private final TransferStrategy strategy;
    public P2PPaymentProcessor(TransferStrategy s){this.strategy=s;}
    protected void executeTransfer(Payment p)throws Exception{ strategy.executeTransfer(p);}
}
