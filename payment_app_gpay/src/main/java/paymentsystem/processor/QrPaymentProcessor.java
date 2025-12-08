
package paymentsystem.processor;
import paymentsystem.domain.Payment;
import paymentsystem.strategies.TransferStrategy;
public class QrPaymentProcessor extends BasePaymentProcessor {
    private final TransferStrategy strategy;
    public QrPaymentProcessor(TransferStrategy s){this.strategy=s;}
    protected void executeTransfer(Payment p)throws Exception{ strategy.executeTransfer(p);}
}
